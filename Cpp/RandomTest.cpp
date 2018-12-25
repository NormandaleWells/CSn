
#define CATCH_CONFIG_MAIN
#include "catch.hpp"

#include "StdRandom.h"

#include <algorithm>
#include <cmath>
#include <iostream>
#include <iterator>
#include <numeric>

using namespace CSn;

// Remember, we are not testing the quality of the C++ random number
// generator; we're only guarding against dumb mistakes in our use of it.

TEST_CASE("random() returns a number in the proper range", "[StdRandom]")
{
	for (int i = 0; i < 10; i++)
	{
		double r = random();
		REQUIRE(r >= 0.0);
		REQUIRE(r < 1.0);
	}
}

TEST_CASE("seed() resets the RNG starting point", "[StdRandom]")
{
	set_seed(0);
	double r = random();
	double s = random();
	set_seed(0);
	s = random();
	REQUIRE(s == r);
}

TEST_CASE("uniform() returns a number in the proper range", "[StdRandom]")
{
	set_seed(0);
	for (size_t i = 0; i < 10; i++)
	{
		int r = uniform(0, 10);
		REQUIRE(r >= 0);
		REQUIRE(r < 10);
	}
}

TEST_CASE("uniform(int) provides results in the correct range", "[StdRandom]")
{
	set_seed(0);
	std::vector<int> vi(10);
	std::fill(vi.begin(), vi.end(), 0);
	for (size_t i = 0; i < vi.size() * 10; i++)
		vi[uniform(10, 20) - 10] += 1;

	// We should have incrermented vi.size() times.
	int a = std::accumulate(vi.begin(), vi.end(), 0);
	REQUIRE(a == vi.size() * 10);

	// If the maximum entry is as high as 20, we probably have an error
	// in our code.
	int mx = *std::max_element(vi.begin(), vi.end());
	REQUIRE(mx < 20);

	// If the minimum entry is as low as 2, we probably have an error
	// in our code.
	int mn = *std::min_element(vi.begin(), vi.end());
	REQUIRE(mn > 2);
}

TEST_CASE("uniform(double) provides results in the correct range", "[StdRandom]")
{
	set_seed(0);
	std::vector<double> vd(100);
	std::generate(vd.begin(), vd.end(), []() { return uniform(10.0, 20.0); });

	double a = std::accumulate(vd.begin(), vd.end(), 0.0);
	REQUIRE(a > 1400.0);
	REQUIRE(a < 1600.0);

	double mn = *std::min_element(vd.begin(), vd.end());
	REQUIRE(mn >= 10.0);

	double mx = *std::max_element(vd.begin(), vd.end());
	REQUIRE(mn < 20.0);
}

TEST_CASE("bernoulli provides reasonable results", "[StdRandom]")
{
	int u25 = 0;
	int u50 = 0;
	int u75 = 0;
	for (int i = 0; i < 1000; i++)
	{
		u25 += CSn::bernoulli(0.25) ? 1 : 0;
		u50 += CSn::bernoulli(0.50) ? 1 : 0;
		u75 += CSn::bernoulli(0.75) ? 1 : 0;
	}

	REQUIRE(u25 > 200);
	REQUIRE(u25 < 300);
	REQUIRE(u50 > 450);
	REQUIRE(u50 < 550);
	REQUIRE(u75 > 700);
	REQUIRE(u75 < 800);
}

TEST_CASE("gaussian provides reasonable results", "[StdRandom]")
{
	set_seed(0);
	std::vector<int> v(21);
	std::fill(v.begin(), v.end(), 0);
	for (size_t i = 0; i < 10000; i++)
	{
		double val = CSn::gaussian();
		if (-2.1 < val && val < 2.1)
		{
			// multiply by 5 to get (-10.5 .. 10.5)
			// add 0.5 to get (-10.0 .. 11.0)
			// floor to get [-10, 11)
			// convert to int to get [-10, 11)
			// add 10 to get [0, 21)
			int i = static_cast<int>(floor(val * 5.0 + 0.5)) + 10;
			v[i] += 1;
		}
	}

	// NOTE: As we go through the indices of v, we should theoretically
	// get increasing numbers from 0..10, and then decreasing from 10..20.
	// Howevere, it seems to be common enough that either
	//		v[i] > v[i+1] for i = [0..10)
	//		v[i] < v[i-1] for i = (10..20]
	// that instead we'll compare each value to the one two past it.
	for (int i = 0; i < 8; i++)
	{
		REQUIRE(v[i] < v[i + 2]);
		REQUIRE(v[21 - i - 1] < v[21 - i - 3]);
	}
}

TEST_CASE("discrete provides reasonable results", "[StdRandom]")
{
	set_seed(0);
	std::vector<int> v(4);
	std::fill(v.begin(), v.end(), 0);
	std::vector<double> d = { 0.1, 0.4, 0.1, 0.4 };
	for (size_t i = 0; i < 10000; i++)
	{
		v[CSn::discrete(d)] += 1;
	}

	REQUIRE(v[0] > 950);
	REQUIRE(v[0] < 1050);
	REQUIRE(v[1] > 3750);
	REQUIRE(v[1] < 4250);
	REQUIRE(v[2] > 950);
	REQUIRE(v[2] < 1050);
	REQUIRE(v[3] > 3750);
	REQUIRE(v[3] < 4250);
}

bool is_sorted(const std::vector<double> & v)
{
	for (size_t i = 1; i < v.size(); i++)
	{
		if (v[i] < v[i - 1]) return false;
	}
	return true;
}

TEST_CASE("shuffle shuffles", "[StdRandom]")
{
	SECTION("with doubles")
	{
		std::vector<double> v(10);
		std::iota(v.begin(), v.end(), 0.0);
		CSn::shuffle(v);
		REQUIRE_FALSE(is_sorted(v));
		std::sort(v.begin(), v.end());
		for (size_t i = 0; i < v.size(); i++)
		{
			REQUIRE(v[i] == i);
		}
	}

	SECTION("with ints")
	{
		std::vector<double> v(10);
		std::iota(v.begin(), v.end(), 0);
		CSn::shuffle(v);
		REQUIRE_FALSE(is_sorted(v));
		std::sort(v.begin(), v.end());
		for (size_t i = 0; i < v.size(); i++)
		{
			REQUIRE(v[i] == i);
		}
	}
}
