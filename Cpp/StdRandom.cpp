
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

unsigned int uniform(unsigned int n)
{
	return static_cast<int>(random() * n);
}

int uniform(int low, int high)
{
	unsigned int range = high - low;
	return low + uniform(range);
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

index discrete(const Array<double> & a)
{
	double r = random();
	double sum = 0.0;
	for (index i = 0; i < a.size(); i++)
	{
		sum += a[i];
		if (sum >= r) return i;
	}
	return a.size();
}

};
