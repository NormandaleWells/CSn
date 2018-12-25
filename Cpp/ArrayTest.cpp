
#define CATCH_CONFIG_MAIN
#include "catch.hpp"

#include "Array.h"
#include "StdArray.h"

#include <vector>

using namespace CSn;

TEST_CASE("class Array")
{
	SECTION("constructors", "[Array]")
	{
		SECTION("default constructor")
		{
			Array<int> a;
			REQUIRE(a.size() == 0);
		}
		SECTION("size constructor")
		{
			Array<int> a(10);
			REQUIRE(a.size() == 10);
		}
		SECTION("size and value constructor")
		{
			Array<int> a(4, 42);
			REQUIRE(a.size() == 4);
		}
		SECTION("initializer_list constructor")
		{
			Array<int> a = { 2, 3, 5, 7 };
			REQUIRE(a.size() == 4);
		}
		SECTION("move constructor")
		{
			Array<int> a = { 2, 3, 5, 7 };
			Array<int> b(std::move(a));
			REQUIRE(a.size() == 0);
			REQUIRE(b.size() == 4);
		}
	}
	SECTION("operator[]")
	{
		SECTION("with default constructor")
		{
			Array<int> a;
			REQUIRE_THROWS_AS(a[0] == 0, std::out_of_range);
		}
		SECTION("with size and value constructor")
		{
			Array<int> a(4, 42);
			REQUIRE(a[0] == 42);
			REQUIRE(a[1] == 42);
			REQUIRE(a[2] == 42);
			REQUIRE(a[3] == 42);
			REQUIRE_THROWS_AS(a[4] == 0, std::out_of_range);
		}
		SECTION("with initializer_list constructor")
		{
			Array<int> a = { 2, 3, 5, 7 };
			REQUIRE(a[0] == 2);
			REQUIRE(a[1] == 3);
			REQUIRE(a[2] == 5);
			REQUIRE(a[3] == 7);
			REQUIRE_THROWS_AS(a[4] == 0, std::out_of_range);
		}
		SECTION("with move constructor")
		{
			Array<int> a = { 2, 3, 5, 7 };
			Array<int> b(std::move(a));
			REQUIRE(b[0] == 2);
			REQUIRE(b[1] == 3);
			REQUIRE(b[2] == 5);
			REQUIRE(b[3] == 7);
			REQUIRE_THROWS_AS(a[0] == 0, std::out_of_range);
			REQUIRE_THROWS_AS(a[4] == 0, std::out_of_range);
		}
	}
	SECTION("operator==")
	{
		Array<int> a1 = { 2, 3, 5, 7 };
		Array<int> a2 = { 2, 3, 5, 7 };
		Array<int> a3 = { 2, 3, 5, 11 };
		Array<int> a4 = { 2, 3, 5, 7, 11 };
		REQUIRE(a1 == a1);
		REQUIRE(a1 == a2);
		REQUIRE_FALSE(a1 == a3);
		REQUIRE_FALSE(a1 == a4);
	}
	SECTION("move operator=")
	{
		Array<int> a = { 2, 3, 5, 7 };
		Array<int> b = { 7, 5, 3, 2 };
		b = std::move(a);
		REQUIRE(b[0] == 2);
		REQUIRE(b[1] == 3);
		REQUIRE(b[2] == 5);
		REQUIRE(b[3] == 7);
		REQUIRE_THROWS_AS(a[0] == 0, std::out_of_range);
		REQUIRE_THROWS_AS(a[4] == 0, std::out_of_range);
	}
}

TEST_CASE("find()", "[StdArray]")
{
	Array<int> vi{ 1, 42, 17, 33, 57, 19 };
	SECTION("find a value in a range")
	{
		REQUIRE(find(vi, 0, 6, 1) == 0);
		REQUIRE(find(vi, 1, 6, 1) == 6);
		REQUIRE(find(vi, 0, 6, 19) == 5);
		REQUIRE(find(vi, 0, 5, 1) == 0);
		REQUIRE(find(vi, 1, 5, 0) == 5);
		REQUIRE(find(vi, 1, 5, 50) == 5);
		REQUIRE(find(vi, 1, 5, 17) == 2);
		REQUIRE(find(vi, 1, 5, 33) == 3);
	}
	SECTION("find a value in the entire array")
	{
		REQUIRE(find(vi, 1) == 0);
		REQUIRE(find(vi, 19) == 5);
		REQUIRE(find(vi, 0) == 6);
		REQUIRE(find(vi, 50) == 6);
		REQUIRE(find(vi, 17) == 2);
		REQUIRE(find(vi, 33) == 3);
	}
	SECTION("find a value in a range using a predicate")
	{
		REQUIRE(find(vi, 0, 6, [](int i) { return i > 45; }) == 4);
		REQUIRE(find(vi, 1, 5, [](int i) { return i > 45; }) == 4);
		REQUIRE(find(vi, 0, 6, [](int i) { return i < 0; }) == 6);
		REQUIRE(find(vi, 1, 5, [](int i) { return i < 0; }) == 5);
	}
	SECTION("find a value in the entire array using a predicate")
	{
		REQUIRE(find(vi, [](int i) { return i > 45; }) == 4);
		REQUIRE(find(vi, [](int i) { return i < 0; }) == 6);
	}
}

TEST_CASE("binary_search() is working", "[StdArray]")
{
	Array<int> vi = { 13, 17, 19, 23, 29, 31, 37 };

	SECTION("with a subrange of the vector")
	{
		REQUIRE(binary_search(vi, 1, 6, 13) == -1);
		REQUIRE(binary_search(vi, 1, 6, 17) == 1);
		REQUIRE(binary_search(vi, 1, 6, 19) == 2);
		REQUIRE(binary_search(vi, 1, 6, 23) == 3);
		REQUIRE(binary_search(vi, 1, 6, 29) == 4);
		REQUIRE(binary_search(vi, 1, 6, 31) == 5);
		REQUIRE(binary_search(vi, 1, 6, 37) == -1);
		REQUIRE(binary_search(vi, 1, 6, 11) == -1);
		REQUIRE(binary_search(vi, 1, 6, 41) == -1);
		REQUIRE(binary_search(vi, 1, 6, 15) == -1);
		REQUIRE(binary_search(vi, 1, 6, 34) == -1);
		REQUIRE(binary_search(vi, 1, 6, 21) == -1);
		REQUIRE(binary_search(vi, 1, 6, 26) == -1);
	}
	SECTION("with the entire vector")
	{
		REQUIRE(binary_search(vi, 13) == 0);
		REQUIRE(binary_search(vi, 23) == 3);
		REQUIRE(binary_search(vi, 37) == 6);
		REQUIRE(binary_search(vi, 11) == -1);
		REQUIRE(binary_search(vi, 41) == -1);
		REQUIRE(binary_search(vi, 15) == -1);
		REQUIRE(binary_search(vi, 34) == -1);
		REQUIRE(binary_search(vi, 21) == -1);
		REQUIRE(binary_search(vi, 26) == -1);
	}
}

TEST_CASE("lower_bound() is working", "[StdArray]")
{
	Array<int> vi = { 13, 17, 19, 23, 29, 31, 37 };
	Array<int> v1 = { 1, 1, 2, 3 };
	Array<int> v2 = { 1, 2, 2, 3 };
	Array<int> v3 = { 1, 2, 3, 3 };

	SECTION("with a subrange of the vector")
	{
		REQUIRE(lower_bound(vi, 1, 6, 13) == 1);
		REQUIRE(lower_bound(vi, 1, 6, 17) == 1);
		REQUIRE(lower_bound(vi, 1, 6, 19) == 2);
		REQUIRE(lower_bound(vi, 1, 6, 23) == 3);
		REQUIRE(lower_bound(vi, 1, 6, 29) == 4);
		REQUIRE(lower_bound(vi, 1, 6, 31) == 5);
		REQUIRE(lower_bound(vi, 1, 6, 37) == 6);
		REQUIRE(lower_bound(vi, 1, 6, 11) == 1);
		REQUIRE(lower_bound(vi, 1, 6, 41) == 6);
		REQUIRE(lower_bound(vi, 1, 6, 15) == 1);
		REQUIRE(lower_bound(vi, 1, 6, 34) == 6);
		REQUIRE(lower_bound(vi, 1, 6, 21) == 3);
		REQUIRE(lower_bound(vi, 1, 6, 26) == 4);
	}
	SECTION("with the entire vector")
	{
		REQUIRE(lower_bound(vi, 13) == 0);
		REQUIRE(lower_bound(vi, 23) == 3);
		REQUIRE(lower_bound(vi, 37) == 6);
		REQUIRE(lower_bound(vi, 11) == 0);
		REQUIRE(lower_bound(vi, 41) == 7);
		REQUIRE(lower_bound(vi, 15) == 1);
		REQUIRE(lower_bound(vi, 34) == 6);
		REQUIRE(lower_bound(vi, 21) == 3);
		REQUIRE(lower_bound(vi, 26) == 4);
	}
	SECTION("with duplicate values")
	{
		REQUIRE(lower_bound(v1, 1) == 0);
		REQUIRE(lower_bound(v1, 2) == 2);
		REQUIRE(lower_bound(v1, 3) == 3);
		REQUIRE(lower_bound(v2, 1) == 0);
		REQUIRE(lower_bound(v2, 2) == 1);
		REQUIRE(lower_bound(v2, 3) == 3);
		REQUIRE(lower_bound(v3, 1) == 0);
		REQUIRE(lower_bound(v3, 2) == 1);
		REQUIRE(lower_bound(v3, 3) == 2);
	}
}

TEST_CASE("upper_bound() is working", "[StdArray]")
{
	Array<int> vi = { 13, 17, 19, 23, 29, 31, 37 };
	Array<int> v1 = { 1, 1, 2, 3 };
	Array<int> v2 = { 1, 2, 2, 3 };
	Array<int> v3 = { 1, 2, 3, 3 };

	SECTION("with a subrange of the vector")
	{
		REQUIRE(upper_bound(vi, 1, 6, 13) == 1);
		REQUIRE(upper_bound(vi, 1, 6, 17) == 2);
		REQUIRE(upper_bound(vi, 1, 6, 19) == 3);
		REQUIRE(upper_bound(vi, 1, 6, 23) == 4);
		REQUIRE(upper_bound(vi, 1, 6, 29) == 5);
		REQUIRE(upper_bound(vi, 1, 6, 31) == 6);
		REQUIRE(upper_bound(vi, 1, 6, 37) == 6);
		REQUIRE(upper_bound(vi, 1, 6, 11) == 1);
		REQUIRE(upper_bound(vi, 1, 6, 41) == 6);
		REQUIRE(upper_bound(vi, 1, 6, 15) == 1);
		REQUIRE(upper_bound(vi, 1, 6, 34) == 6);
		REQUIRE(upper_bound(vi, 1, 6, 21) == 3);
		REQUIRE(upper_bound(vi, 1, 6, 26) == 4);
	}
	SECTION("with the entire vector")
	{
		REQUIRE(upper_bound(vi, 13) == 1);
		REQUIRE(upper_bound(vi, 23) == 4);
		REQUIRE(upper_bound(vi, 37) == 7);
		REQUIRE(upper_bound(vi, 11) == 0);
		REQUIRE(upper_bound(vi, 41) == 7);
		REQUIRE(upper_bound(vi, 15) == 1);
		REQUIRE(upper_bound(vi, 34) == 6);
		REQUIRE(upper_bound(vi, 21) == 3);
		REQUIRE(upper_bound(vi, 26) == 4);
	}
	SECTION("with duplicate values")
	{
		REQUIRE(upper_bound(v1, 1) == 2);
		REQUIRE(upper_bound(v1, 2) == 3);
		REQUIRE(upper_bound(v1, 3) == 4);
		REQUIRE(upper_bound(v2, 1) == 1);
		REQUIRE(upper_bound(v2, 2) == 3);
		REQUIRE(upper_bound(v2, 3) == 4);
		REQUIRE(upper_bound(v3, 1) == 1);
		REQUIRE(upper_bound(v3, 2) == 2);
		REQUIRE(upper_bound(v3, 3) == 4);
	}
}

TEST_CASE("copy() is working", "[StdArray]")
{
	Array<int> vi = { 13, 17, 19, 23, 29, 31, 37 };
	Array<int> v01 = { 13 };
	Array<int> v04 = { 13, 17, 19, 23 };
	Array<int> v34 = { 23 };
	Array<int> v37 = { 23, 29, 31, 37 };

	Array<int> test1(1);
	Array<int> test4(4);
	Array<int> test7(7);

	copy(vi, 0, 7, test7, 0);
	REQUIRE(test7 == vi);
	copy(vi, 0, 1, test1, 0);
	REQUIRE(test1 == v01);
	copy(vi, 3, 4, test1, 0);
	REQUIRE(test1 == v34);
	copy(vi, 0, 4, test4, 0);
	REQUIRE(test4 == v04);
	copy(vi, 3, 7, test4, 0);
	REQUIRE(test4 == v37);
	copy(vi, 0, 4, test7, 0);
	copy(vi, 3, 7, test7, 3);
	REQUIRE(test7 == vi);
	REQUIRE_THROWS_AS(copy(vi, 0, 8, test7, 0), std::out_of_range);
	REQUIRE_THROWS_AS(copy(vi, 0, 4, test1, 0), std::out_of_range);
}
