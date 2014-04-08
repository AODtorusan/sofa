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
WINKIT_PATH='C:/Program Files (x86)/Windows Kits/8.1/Debuggers/x64/srcsrv'

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
# ArgN: CMake args

    arch="$1"
    shift
    cmake_args=($@)
    if [[ $IS_WIN ]]; then
        # Windows/cygwin
        # For windows, generate a ".bat" script that does the actual building, and call that script
        # 1) Load msvc
        # 2) Call cmake
        # 3) Call make
        
        if [[ $USE_MSVC_OVER_GCC ]]; then
            # MSVC
            SOURCE_SERVER='C:/Program Files (x86)/Windows Kits/8.1/Debuggers/x64/srcsrv'
            if [[ $arch == "x64" ]]; then 
                vc_arch=amd64;
                LIBRARY_DIR=${SOURCE_DIR}/../resources/lib/win64
            else 
                vc_arch=x86; 
                LIBRARY_DIR=${SOURCE_DIR}/../resources/lib/win32
            fi
            read -d '' load_compiler <<EOF
REM echo Adjusting PATH enviromental variable ...
REM echo %PATH% | sed -e "s|[^;]*cygwin[^;]*||g" -e "s|[^;]*MinGW64[^;]*||g" > path
REM set /p PATH=<path
REM del path
echo Loading msvc $vc_arch binaries ...
CALL "${VC_PATH}/VC/vcvarsall.bat" $vc_arch
EOF
            
            # Complain if the Git Source Server command is not present
            export PATH=$PATH:"$(cygpath -au "$WINKIT_PATH")"
            if hash gitindex.cmd 2>/dev/null; then
                read -d '' finish_build <<EOF
gitindex.cmd /debug /source=${SOURCE_DIR} /symbols=${LIBRARY_DIR}
echo Build finished
EOF
            else
                echo "Missing gitindex.cmd on path, this is required to embed Source information into the PDB."
                echo "For setting up the Git Source Server, see http://github.com/joliver/SourceServer-GitExtensions"
                finish_build="echo Build finished"
            fi
        else
            # MingGW
            if [[ $arch == x64 ]]; then BUILDFLAG="-m64"; else BUILDFLAG="-m32"; fi
            cmake_args+=("-D CMAKE_CXX_FLAGS=$BUILDFLAG ");
            cmake_args+=("-D CMAKE_C_FLAGS=$BUILDFLAG ");
            cmake_args+=("-D CMAKE_LD_FLAGS=$BUILDFLAG ");
            load_compiler="echo Using cmake/gcc in  $BUILDFLAG mode ($arch) ..."
            finish_build="echo Build finished"
        fi
        
        rm -f bld.exec.bat
        cat << EOF > bld.exec.bat
@echo off
${load_compiler}
echo Running CMake ...
cmake -G "$cmake_generator" ${cmake_args[@]}  $SOURCE_DIR
echo Building sources with $make_exec ...
$make_exec
${finish_build}
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

if [[ $platform == "macos" ]]; then
    # Build the universal version of the library
    mkdir -p universal
    cd universal
    buildLibrary universal "-DCMAKE_OSX_ARCHITECTURES=x86_64;i386"
    cd ..
else
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
fi