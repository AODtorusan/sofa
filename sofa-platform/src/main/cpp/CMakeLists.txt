cmake_minimum_required (VERSION 2.6)
project (sofa)

#-----------------------------------------------------------------------------#
# Configure cmake                                                             #
#-----------------------------------------------------------------------------#

CMAKE_POLICY(SET CMP0012 NEW)

set(CMAKE_BUILD_TYPE Debug)

SET(CMAKE_MODULE_PATH "${PROJECT_SOURCE_DIR}/CMakeModules" ${CMAKE_MODULE_PATH})

include( "BuildTarget" )

# Set binairy output directory to what bridj expects:
# src/main/resources/lib/xyz where xyz is win32, win64, linux_x86, linux_x64, etc ...
set(CMAKE_BINARY_DIR               ${CMAKE_SOURCE_DIR}/../resources/lib/${buildTarget})
set(CMAKE_RUNTIME_OUTPUT_DIRECTORY ${CMAKE_BINARY_DIR})
set(CMAKE_LIBRARY_OUTPUT_DIRECTORY ${CMAKE_BINARY_DIR})
set(CMAKE_ARCHIVE_OUTPUT_DIRECTORY ${CMAKE_BINARY_DIR}) 
message(STATUS "Binairies output directory: ${CMAKE_BINARY_DIR}")

#-----------------------------------------------------------------------------#
# Build sofa                                                                  #
#-----------------------------------------------------------------------------#

set(SOFA_SRC "${CMAKE_SOURCE_DIR}/src/")

file(GLOB_RECURSE SOFA_HEADERS RELATIVE ${PROJECT_SOURCE_DIR} ${SOFA_SRC}/*.h)
file(GLOB_RECURSE SOFA_SOURCES RELATIVE ${PROJECT_SOURCE_DIR} ${SOFA_SRC}/*.c)
message(STATUS "Sofa headers: ${SOFA_HEADERS}")
message(STATUS "Sofa sources: ${SOFA_SOURCES}")

# Add dll exports for visual studio
if(MSVC)
  set(CMAKE_EXE_LINKER_FLAGS "${CMAKE_EXE_LINKER_FLAGS} /DEF:exports.def")
endif()

include_directories(${SOFA_SRC})
add_library( sofa SHARED ${SOFA_HEADERS} ${SOFA_SOURCES})
