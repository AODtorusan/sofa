#!/bin/bash
# This is the script called by maven to build the sofa libraries.
#
# This script initiates the actual build process for both x86 and x64. 
# Note, this build process is different for windows and unix.

set -x

if [[ $(pwd) =~ /target/cmake$ ]]; then
    SOURCE_DIR="../../../src/main/cpp"
else
    SOURCE_DIR="../"
fi
VC_PATH='C:/Program Files (x86)/Microsoft Visual Studio 11.0'

IS_WIN=$( echo "$(uname -s)" | grep -i "CYGWIN"  )
USE_MSVC_OVER_GCC=1

if [[ $IS_WIN && $USE_MSVC_OVER_GCC ]]; then
    export CMAKE_MAKE_PROGRAM="nmake"
    cmake_generator="NMake Makefiles"
    make_exec="nmake"
elif [[ $IS_WIN && $USE_MSVC_OVER_GCC ]]; then
    cmake_generator="MinGW Makefiles"
    make_exec="make"
else
    cmake_generator="Unix Makefiles"
    make_exec="make"
fi

function buildLibrary() {
# Arg1: Architecture

    arch="$1"
    cmake_args=()
    if [[ $IS_WIN ]]; then
        # Windows/cygwin
        # For windows, generate a ".bat" script that does the actual building, and call that script
        # 1) Load msvc
        # 2) Call cmake
        # 3) Call make
        
        if [[ $USE_MSVC_OVER_GCC ]]; then
            # MSVC
            if [[ $arch == "x64" ]]; then vc_arch=amd64; else vc_arch=x86; fi
            read -d '' load_compiler <<EOF
echo Adjusting PATH enviromental variable ...
echo %PATH% | sed -e "s|[^;]*cygwin[^;]*||g" -e "s|[^;]*MinGW64[^;]*||g" > path
set /p PATH=<path
del path
echo Loading msvc $vc_arch binaries ...
CALL "${VC_PATH}/VC/vcvarsall.bat" $vc_arch
EOF
        else
            # MingGW
            if [[ $arch == x64 ]]; then BUILDFLAG="-m64"; else BUILDFLAG="-m32"; fi
            cmake_args+=("-D CMAKE_CXX_FLAGS=$BUILDFLAG ");
            cmake_args+=("-D CMAKE_C_FLAGS=$BUILDFLAG ");
            cmake_args+=("-D CMAKE_LD_FLAGS=$BUILDFLAG ");
            load_compiler="echo Using cmake/gcc in  $BUILDFLAG mode ($arch) ..."
        fi
        
        rm -f bld.exec.bat
        cat << EOF > bld.exec.bat
@echo off
${load_compiler}
echo Running CMake ...
cmake -G "$cmake_generator" ${cmake_args[@]}  $SOURCE_DIR
echo Building sources with $make_exec ...
$make_exec
EOF
        chmod +x bld.exec.bat
        ./bld.exec.bat
        #rm  bld.exec.bat
    else
        # Unix
        # Cmake and make can be called directly from the script
        
        if [[ $arch == x64 ]]; then BUILDFLAG="-m64"; else BUILDFLAG="-m32"; fi
        cmake_args+=("-D CMAKE_CXX_FLAGS=$BUILDFLAG ");
        cmake_args+=("-D CMAKE_C_FLAGS=$BUILDFLAG ");
        cmake_args+=("-D CMAKE_LD_FLAGS=$BUILDFLAG ");
            
        cmake -G "Unix Makefiles" ${cmake_args[@]} $SOURCE_DIR
        make
    fi
}

# Build the X86 version of the library
mkdir -p x86
cd x86
buildLibrary x86
cd ..

# Build the X64 version of the library
mkdir -p  x64
cd x64
buildLibrary x64
cd ..
