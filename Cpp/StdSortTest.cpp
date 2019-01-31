
#define CATCH_CONFIG_MAIN
#include "catch.hpp"

#include <algorithm>
#include <numeric>

#include "StdSort.h"

using namespace CSn;

TEST_CASE("selection_sort()", "[StdSort]")
{
	SECTION("test an empty array")
	{
		Array<int> v;
		REQUIRE_NOTHROW(selection_sort(v));
	}
	SECTION("test a one-element array")
	{
		Array<int> v{ 0 };
		REQUIRE_NOTHROW(selection_sort(v));
	}
	SECTION("test a two-element sorted array")
	{
		Array<int> v{ 0, 1 };
		REQUIRE_NOTHROW(selection_sort(v));
		REQUIRE(v[0] == 0);
		REQUIRE(v[1] == 1);
	}
	SECTION("test a two-element unsorted array")
	{
		Array<int> v{ 1, 0 };
		REQUIRE_NOTHROW(selection_sort(v));
		REQUIRE(v[0] == 0);
		REQUIRE(v[1] == 1);
	}
	SECTION("test every possible 6-element array")
	{
		Array<int> v(8);
		std::iota(v.begin(), v.end(), 0);
		Array<int> v_orig(v);
		while (std::next_permutation(v.begin(), v.end()))
		{
			Array<int> v_sort(v);
			selection_sort(v_sort);
			REQUIRE(v_sort == v_orig);
		}
	}
}

TEST_CASE("insertion_sort()", "[StdSort]")
{
	SECTION("test an empty array")
	{
		Array<int> v;
		REQUIRE_NOTHROW(insertion_sort(v));
	}
	SECTION("test a one-element array")
	{
		Array<int> v{ 0 };
		REQUIRE_NOTHROW(insertion_sort(v));
	}
	SECTION("test a two-element sorted array")
	{
		Array<int> v{ 0, 1 };
		REQUIRE_NOTHROW(insertion_sort(v));
		REQUIRE(v[0] == 0);
		REQUIRE(v[1] == 1);
	}
	SECTION("test a two-element unsorted array")
	{
		Array<int> v{ 1, 0 };
		REQUIRE_NOTHROW(insertion_sort(v));
		REQUIRE(v[0] == 0);
		REQUIRE(v[1] == 1);
	}
	SECTION("test every possible 6-element array")
	{
		Array<int> v(8);
		std::iota(v.begin(), v.end(), 0);
		Array<int> v_orig(v);
		while (std::next_permutation(v.begin(), v.end()))
		{
			Array<int> v_sort(v);
			insertion_sort(v_sort);
			REQUIRE(v_sort == v_orig);
		}
	}
}
