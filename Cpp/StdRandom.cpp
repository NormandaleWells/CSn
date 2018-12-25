
#include "StdRandom.h"

#include <random>

namespace CSn
{

// NOTE: This is NOT thread-safe - we need to use thread-local storage
static std::mt19937_64 rng;

void set_seed(long seed)
{
	rng.seed(seed);
}

double random()
{
	std::uniform_real_distribution<double> unif(0, 1);
	return unif(rng);
}

int uniform(int n)
{
	return static_cast<int>(random() * n);
}

int uniform(int low, int high)
{
	return low + uniform(high - low);
}

double uniform(double low, double high)
{
	return low + random() * (high - low);
}

bool bernoulli(double p)
{
	std::bernoulli_distribution bd(p);
	return bd(rng);
}

double gaussian()
{
	return gaussian(0, 1);
}

double gaussian(double m, double s)
{
	std::normal_distribution<double> nd(m, s);
	return nd(rng);
}

size_t discrete(const std::vector<double> & a)
{
	double r = random();
	double sum = 0.0;
	for (size_t i = 0; i < a.size(); i++)
	{
		sum += a[i];
		if (sum >= r) return i;
	}
	return a.size();
}

};
