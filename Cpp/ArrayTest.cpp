
#define CATCH_CONFIG_MAIN
#include "catch.hpp"

#include "Array.h"

#include <algorithm>
#include <numeric>

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
	SECTION("iteration")
	{
		SECTION("using begin() and end()")
		{
			Array<int> a = { 2, 3, 5, 7 };
			Array<int> b = { 7, 5, 3, 2 };
			for (auto ai = a.begin(), bi = b.begin(); ai != a.end(); ++ai, ++bi)
			{
				*bi = *ai;
			}
			REQUIRE(a[0] == 2);
			REQUIRE(a[1] == 3);
			REQUIRE(a[2] == 5);
			REQUIRE(a[3] == 7);
			REQUIRE(b[0] == 2);
			REQUIRE(b[1] == 3);
			REQUIRE(b[2] == 5);
			REQUIRE(b[3] == 7);
		}
		SECTION("using range for")
		{
			Array<int> a = { 2, 3, 5, 7 };
			Array<int> b = { 7, 5, 3, 2 };
			index i = 0;
			for (int x : a)
			{
				b[i++] = x;
			}
			REQUIRE(a[0] == 2);
			REQUIRE(a[1] == 3);
			REQUIRE(a[2] == 5);
			REQUIRE(a[3] == 7);
			REQUIRE(b[0] == 2);
			REQUIRE(b[1] == 3);
			REQUIRE(b[2] == 5);
			REQUIRE(b[3] == 7);
		}
		SECTION("using STL algorithms")
		{
			Array<int> a(4);
			std::iota(a.begin(), a.end(), 0);
			REQUIRE(a[0] == 0);
			REQUIRE(a[1] == 1);
			REQUIRE(a[2] == 2);
			REQUIRE(a[3] == 3);
			Array<int> b(a.size());
			std::copy(a.begin(), a.end(), b.begin());
			REQUIRE(b[0] == 0);
			REQUIRE(b[1] == 1);
			REQUIRE(b[2] == 2);
			REQUIRE(b[3] == 3);
		}
	}
}
