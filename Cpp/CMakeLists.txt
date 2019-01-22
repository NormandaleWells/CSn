cmake_minimum_required(VERSION 3.9)
if(CMAKE_CXX_FLAGS MATCHES "/W[0-4]")
	string(REGEX REPLACE "/W[0-4]" "/W4" CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS}")
else()
	set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} /W4")
endif()

project (ArrayTest)
add_executable(ArrayTest ArrayTest.cpp)

project (RandomTest)
add_executable(RandomTest RandomTest.cpp StdRandom.cpp)
