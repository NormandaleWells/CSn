
#ifndef STDRANDOM_H
#define STDRANDOM_H

#include <vector>

namespace CSn
{

// initialize
void set_seed(long seed);

// return double in range [0,1)
double random();

// return integer in range [0,n)
int uniform(int n);

// return integer in range [high,low)
int uniform(int low, int high);

// return double in range [low,high]
double uniform(double low, double high);

// return true with probability p
bool bernoulli(double p);

// normal, mean 0, std dev 1
double gaussian();

// normal, mean m, std dev s
double gaussian(double m, double s);

// return i with probability a[i]
// NOTE: sum of values in a must be 1.0
size_t discrete(const std::vector<double> & a);

// randomly shuffle a
template <typename T>
void shuffle(std::vector<T> & a)
{
	size_t n = a.size();
	for (size_t i = 0; i < n; i++)
	{
		int r = i + uniform(n - i);
		std::swap(a[i], a[r]);
	}
}

};

#endif
