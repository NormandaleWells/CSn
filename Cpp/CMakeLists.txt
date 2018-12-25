cmake_minimum_required(VERSION 3.9)

project (ArrayTest)
add_executable(ArrayTest ArrayTest.cpp)

project (RandomTest)
add_executable(RandomTest RandomTest.cpp StdRandom.cpp)
