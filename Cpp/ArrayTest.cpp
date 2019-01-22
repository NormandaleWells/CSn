
#define CATCH_CONFIG_MAIN
#include "catch.hpp"

#include "Array.h"
#include "StdArray.h"

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

TEST_CASE("find()", "[StdArray]")
{
	Array<int> vi{ 1, 42, 17, 33, 57, 19 };
	SECTION("find a value in a range")
	{
		REQUIRE(find(vi, 0, 6, 1) == 0);
		REQUIRE(find(vi, 0, 6, 42) == 1);
		REQUIRE(find(vi, 0, 6, 57) == 4);
		REQUIRE(find(vi, 0, 6, 19) == 5);
		REQUIRE(find(vi, 0, 6, 24) == invalid);

		REQUIRE(find(vi, 1, 6, 1) == invalid);
		REQUIRE(find(vi, 1, 6, 42) == 1);
		REQUIRE(find(vi, 1, 6, 57) == 4);
		REQUIRE(find(vi, 1, 6, 19) == 5);
		REQUIRE(find(vi, 1, 6, 24) == invalid);

		REQUIRE(find(vi, 0, 5, 1) == 0);
		REQUIRE(find(vi, 0, 5, 42) == 1);
		REQUIRE(find(vi, 0, 5, 57) == 4);
		REQUIRE(find(vi, 0, 5, 19) == invalid);
		REQUIRE(find(vi, 0, 5, 24) == invalid);

		REQUIRE(find(vi, 1, 5, 1) == invalid);
		REQUIRE(find(vi, 1, 5, 42) == 1);
		REQUIRE(find(vi, 1, 5, 57) == 4);
		REQUIRE(find(vi, 1, 5, 19) == invalid);
		REQUIRE(find(vi, 1, 5, 24) == invalid);
	}
	SECTION("find a value in the entire array")
	{
		REQUIRE(find(vi, 1) == 0);
		REQUIRE(find(vi, 42) == 1);
		REQUIRE(find(vi, 57) == 4);
		REQUIRE(find(vi, 19) == 5);
		REQUIRE(find(vi, 24) == invalid);
	}
	SECTION("find a value in a range using a predicate")
	{
		REQUIRE(find(vi, 0, 6, [](int i) { return i > 45; }) == 4);
		REQUIRE(find(vi, 1, 5, [](int i) { return i > 45; }) == 4);
		REQUIRE(find(vi, 0, 6, [](int i) { return i % 2 == 0; }) == 1);
		REQUIRE(find(vi, 1, 5, [](int i) { return i % 2 == 0; }) == 1);
		REQUIRE(find(vi, 0, 6, [](int i) { return i > 100; }) == invalid);
		REQUIRE(find(vi, 1, 5, [](int i) { return i > 100; }) == invalid);
	}
	SECTION("find a value in the entire array using a predicate")
	{
		REQUIRE(find(vi, [](int i) { return i > 45; }) == 4);
		REQUIRE(find(vi, [](int i) { return i % 2 == 0; }) == 1);
		REQUIRE(find(vi, [](int i) { return i < 0; }) == invalid);
	}
}

TEST_CASE("count()", "[StdArray]")
{
	Array<int> a1{ 1, 2, 3, 1, 4, 3 };
	Array<int> a2{ 2, 1, 1, 3, 3, 4 };
	SECTION("count values in a range")
	{
		REQUIRE(count(a1, 0, 6, 1) == 2);
		REQUIRE(count(a1, 0, 6, 2) == 1);
		REQUIRE(count(a1, 0, 6, 3) == 2);
		REQUIRE(count(a1, 0, 6, 4) == 1);
		REQUIRE(count(a1, 0, 6, 5) == 0);

		REQUIRE(count(a1, 1, 6, 1) == 1);
		REQUIRE(count(a1, 1, 6, 2) == 1);
		REQUIRE(count(a1, 1, 6, 3) == 2);
		REQUIRE(count(a1, 1, 6, 4) == 1);
		REQUIRE(count(a1, 1, 6, 5) == 0);

		REQUIRE(count(a1, 0, 5, 1) == 2);
		REQUIRE(count(a1, 0, 5, 2) == 1);
		REQUIRE(count(a1, 0, 5, 3) == 1);
		REQUIRE(count(a1, 0, 5, 4) == 1);
		REQUIRE(count(a1, 0, 5, 5) == 0);

		REQUIRE(count(a1, 1, 5, 1) == 1);
		REQUIRE(count(a1, 1, 5, 2) == 1);
		REQUIRE(count(a1, 1, 5, 3) == 1);
		REQUIRE(count(a1, 1, 5, 4) == 1);
		REQUIRE(count(a1, 1, 5, 5) == 0);

		REQUIRE(count(a2, 0, 6, 1) == 2);
		REQUIRE(count(a2, 0, 6, 2) == 1);
		REQUIRE(count(a2, 0, 6, 3) == 2);
		REQUIRE(count(a2, 0, 6, 4) == 1);
		REQUIRE(count(a2, 0, 6, 5) == 0);

		REQUIRE(count(a2, 1, 6, 1) == 2);
		REQUIRE(count(a2, 1, 6, 2) == 0);
		REQUIRE(count(a2, 1, 6, 3) == 2);
		REQUIRE(count(a2, 1, 6, 4) == 1);
		REQUIRE(count(a2, 1, 6, 5) == 0);

		REQUIRE(count(a2, 0, 5, 1) == 2);
		REQUIRE(count(a2, 0, 5, 2) == 1);
		REQUIRE(count(a2, 0, 5, 3) == 2);
		REQUIRE(count(a2, 0, 5, 4) == 0);
		REQUIRE(count(a2, 0, 5, 5) == 0);

		REQUIRE(count(a2, 1, 5, 1) == 2);
		REQUIRE(count(a2, 1, 5, 2) == 0);
		REQUIRE(count(a2, 1, 5, 3) == 2);
		REQUIRE(count(a2, 1, 5, 4) == 0);
		REQUIRE(count(a2, 1, 5, 5) == 0);
	}
	SECTION("find a value in the entire array")
	{
		REQUIRE(count(a1, 1) == 2);
		REQUIRE(count(a1, 2) == 1);
		REQUIRE(count(a1, 3) == 2);
		REQUIRE(count(a1, 4) == 1);
		REQUIRE(count(a1, 5) == 0);
	}
}

TEST_CASE("max_element()", "[StdArray]")
{
	SECTION("max element in a range")
	{
		Array<int> a1{ 1, 1, 0, 0, 1, 0, 0, 1, 1 };
		REQUIRE(max_element(a1, 0, 9) == 0);
		REQUIRE(max_element(a1, 1, 9) == 1);
		REQUIRE(max_element(a1, 2, 9) == 4);
		REQUIRE(max_element(a1, 3, 9) == 4);
		REQUIRE(max_element(a1, 4, 9) == 4);
		REQUIRE(max_element(a1, 5, 9) == 7);
		REQUIRE(max_element(a1, 5, 8) == 7);
		REQUIRE(max_element(a1, 5, 7) == 5);
		REQUIRE(max_element(a1, 5, 6) == 5);
		REQUIRE(max_element(a1, 0, 1) == 0);
		REQUIRE(max_element(a1, 0, 2) == 0);
		REQUIRE(max_element(a1, 1, 2) == 1);
	}
	SECTION("max element in the entire array")
	{
		Array<int> a1{ 1, 0, 0 };
		Array<int> a2{ 0, 1, 0 };
		Array<int> a3{ 0, 0, 1 };
		REQUIRE(max_element(a1) == 0);
		REQUIRE(max_element(a2) == 1);
		REQUIRE(max_element(a3) == 2);
	}
}

TEST_CASE("min_element()", "[StdArray]")
{
	SECTION("min element in a range")
	{
		Array<int> a1{ 0, 0, 1, 1, 0, 1, 1, 0, 0 };
		REQUIRE(min_element(a1, 0, 9) == 0);
		REQUIRE(min_element(a1, 1, 9) == 1);
		REQUIRE(min_element(a1, 2, 9) == 4);
		REQUIRE(min_element(a1, 3, 9) == 4);
		REQUIRE(min_element(a1, 4, 9) == 4);
		REQUIRE(min_element(a1, 5, 9) == 7);
		REQUIRE(min_element(a1, 5, 8) == 7);
		REQUIRE(min_element(a1, 5, 7) == 5);
		REQUIRE(min_element(a1, 5, 6) == 5);
		REQUIRE(min_element(a1, 0, 1) == 0);
		REQUIRE(min_element(a1, 0, 2) == 0);
		REQUIRE(min_element(a1, 1, 2) == 1);
	}
	SECTION("min element in the entire array")
	{
		Array<int> a1{ 0, 1, 1 };
		Array<int> a2{ 1, 0, 1 };
		Array<int> a3{ 1, 1, 0 };
		REQUIRE(min_element(a1) == 0);
		REQUIRE(min_element(a2) == 1);
		REQUIRE(min_element(a3) == 2);
	}
}

TEST_CASE("rotate_left()", "[StdArray]")
{
	SECTION("rotate a range")
	{
		Array<int> a = { 0, 1, 2, 3, 4 };

		Array<int> atest(a);
		rotate_left(atest, 0, 5);
		REQUIRE(atest == Array<int>{ 1, 2, 3, 4, 0 });

		atest = a;
		rotate_left(atest, 1, 5);
		REQUIRE(atest == Array<int>{ 0, 2, 3, 4, 1 });

		atest = a;
		rotate_left(atest, 0, 4);
		REQUIRE(atest == Array<int>{ 1, 2, 3, 0, 4 });

		atest = a;
		rotate_left(atest, 1, 4);
		REQUIRE(atest == Array<int>{ 0, 2, 3, 1, 4 });

		atest = a;
		rotate_left(atest, 0, 1);
		REQUIRE(atest == a);

		atest = a;
		rotate_left(atest, 4, 5);
		REQUIRE(atest == a);

		atest = a;
		rotate_left(atest, 2, 3);
		REQUIRE(atest == a);

		atest = a;
		rotate_left(atest, 0, 0);
		REQUIRE(atest == a);

		atest = a;
		rotate_left(atest, 5, 5);
		REQUIRE(atest == a);

		atest = a;
		rotate_left(atest, 2, 2);
		REQUIRE(atest == a);
	}
	SECTION("rotate a full array")
	{
		Array<int> a5 = { 0, 1, 2, 3, 4 };
		rotate_left(a5);
		REQUIRE(a5 == Array<int>{ 1, 2, 3, 4, 0 });

		Array<int> a1 = { 0 };
		rotate_left(a1);
		REQUIRE(a1 == Array<int>{ 0 });

		Array<int> a0;
		rotate_left(a0);
		REQUIRE(a0 == Array<int>());
	}
}

TEST_CASE("rotate_right()", "[StdArray]")
{
	SECTION("rotate a range")
	{
		Array<int> a = { 0, 1, 2, 3, 4 };

		Array<int> atest(a);
		rotate_right(atest, 0, 5);
		REQUIRE(atest == Array<int>{ 4, 0, 1, 2, 3 });

		atest = a;
		rotate_right(atest, 1, 5);
		REQUIRE(atest == Array<int>{ 0, 4, 1, 2, 3 });

		atest = a;
		rotate_right(atest, 0, 4);
		REQUIRE(atest == Array<int>{ 3, 0, 1, 2, 4 });

		atest = a;
		rotate_right(atest, 1, 4);
		REQUIRE(atest == Array<int>{ 0, 3, 1, 2, 4 });

		atest = a;
		rotate_right(atest, 0, 1);
		REQUIRE(atest == a);

		atest = a;
		rotate_right(atest, 4, 5);
		REQUIRE(atest == a);

		atest = a;
		rotate_right(atest, 2, 3);
		REQUIRE(atest == a);

		atest = a;
		rotate_right(atest, 0, 0);
		REQUIRE(atest == a);

		atest = a;
		rotate_right(atest, 5, 5);
		REQUIRE(atest == a);

		atest = a;
		rotate_right(atest, 2, 2);
		REQUIRE(atest == a);
	}
	SECTION("rotate a full array")
	{
		Array<int> a5 = { 0, 1, 2, 3, 4 };
		rotate_right(a5);
		REQUIRE(a5 == Array<int>{ 4, 0, 1, 2, 3 });

		Array<int> a1 = { 0 };
		rotate_right(a1);
		REQUIRE(a1 == Array<int>{ 0 });

		Array<int> a0;
		rotate_right(a0);
		REQUIRE(a0 == Array<int>());
	}
}

TEST_CASE("swap()", "[StdArray]")
{
	Array<int> a = { 0, 1, 2, 3, 4 };
	swap(a, 0, 4);
	REQUIRE(a == Array<int>{ 4, 1, 2, 3, 0 });
	swap(a, 1, 4);
	REQUIRE(a == Array<int>{ 4, 0, 2, 3, 1 });
	swap(a, 0, 3);
	REQUIRE(a == Array<int>{ 3, 0, 2, 4, 1 });
	swap(a, 1, 3);
	REQUIRE(a == Array<int>{ 3, 4, 2, 0, 1 });
	swap(a, 0, 0);
	REQUIRE(a == Array<int>{ 3, 4, 2, 0, 1 });
	swap(a, 4, 4);
	REQUIRE(a == Array<int>{ 3, 4, 2, 0, 1 });
	swap(a, 2, 2);
	REQUIRE(a == Array<int>{ 3, 4, 2, 0, 1 });
}

TEST_CASE("binary_search()", "[StdArray]")
{
	Array<int> vi = { 13, 17, 19, 23, 29, 31, 37 };

	SECTION("with a subrange of the vector")
	{
		REQUIRE(binary_search(vi, 1, 6, 13) == invalid);
		REQUIRE(binary_search(vi, 1, 6, 17) == 1);
		REQUIRE(binary_search(vi, 1, 6, 19) == 2);
		REQUIRE(binary_search(vi, 1, 6, 23) == 3);
		REQUIRE(binary_search(vi, 1, 6, 29) == 4);
		REQUIRE(binary_search(vi, 1, 6, 31) == 5);
		REQUIRE(binary_search(vi, 1, 6, 37) == invalid);
		REQUIRE(binary_search(vi, 1, 6, 11) == invalid);
		REQUIRE(binary_search(vi, 1, 6, 41) == invalid);
		REQUIRE(binary_search(vi, 1, 6, 15) == invalid);
		REQUIRE(binary_search(vi, 1, 6, 34) == invalid);
		REQUIRE(binary_search(vi, 1, 6, 21) == invalid);
		REQUIRE(binary_search(vi, 1, 6, 26) == invalid);
	}
	SECTION("with the entire vector")
	{
		REQUIRE(binary_search(vi, 13) == 0);
		REQUIRE(binary_search(vi, 23) == 3);
		REQUIRE(binary_search(vi, 37) == 6);
		REQUIRE(binary_search(vi, 11) == invalid);
		REQUIRE(binary_search(vi, 41) == invalid);
		REQUIRE(binary_search(vi, 15) == invalid);
		REQUIRE(binary_search(vi, 34) == invalid);
		REQUIRE(binary_search(vi, 21) == invalid);
		REQUIRE(binary_search(vi, 26) == invalid);
	}
}

TEST_CASE("lower_bound()", "[StdArray]")
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

TEST_CASE("upper_bound()", "[StdArray]")
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

TEST_CASE("copy()", "[StdArray]")
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
