
#ifndef ALGS4_STDARRAY_H
#define ALGS4_STDARRAY_H

#include "Array.h"

#include <cassert>
#include <stdexcept>

namespace CSn
{

template <typename T, typename Pred>
index find(const Array<T> & v, index lo, index hi, Pred p)
{
	assert(0 <= lo);
	assert(lo <= hi);
	assert(hi <= v.size());

	for (index i = lo; i < hi; i++)
	{
		if (p(v[i])) return i;
	}

	return invalid;
}

template <typename T, typename Pred>
index find(const Array<T> & v, Pred p)
{
	return find(v, 0, v.size(), p);
}

template <typename T>
index find(const Array<T> & v, index lo, index hi, T t)
{
	return find(v, lo, hi, [&t](const T & v) { return v == t; });
}

template <typename T>
index find(const Array<T> & v, T t)
{
	return find(v, 0, v.size(), t);
}

template <typename T, typename Pred>
int count(const Array<T> & v, index lo, index hi, Pred p)
{
	assert(0 <= lo);
	assert(lo <= hi);
	assert(hi <= v.size());

	int c = 0;
	for (index i = lo; i < hi; i++)
	{
		if (p(v[i]))
			c++;
	}

	return c;
}

template <typename T, typename Pred>
int count(const Array<T> & v, Pred p)
{
	return count(v, 0, v.size(), p);
}

template <typename T>
int count(const Array<T> & v, index lo, index hi, T t)
{
	return count(v, lo, hi, [&t](const T & v) { return v == t; });
}

template <typename T>
int count(const Array<T> & v, T t)
{
	return count(v, 0, v.size(), t);
}

template <typename T>
index max_element(const Array<T> & v, index lo, index hi)
{
	assert(0 <= lo);
	assert(lo < hi);
	assert(hi <= v.size());

	index idx_max = lo;
	for (index i = lo; i < hi; i++)
		if (v[idx_max] < v[i])
			idx_max = i;
	return idx_max;
}

template <typename T>
index max_element(const Array<T> & v)
{
	return max_element(v, 0, v.size());
}

template <typename T>
index min_element(const Array<T> & v, index lo, index hi)
{
	assert(0 <= lo);
	assert(lo < hi);
	assert(hi <= v.size());

	index idx_min = lo;
	for (index i = lo; i < hi; i++)
		if (v[i] < v[idx_min])
			idx_min = i;
	return idx_min;
}

template <typename T>
index min_element(const Array<T> & v)
{
	return min_element(v, 0, v.size());
}

////////////////////
//
// Note that in all binary search functions, we only use the
// less-than operator.  This minimizes the number of comparison
// operators that need to be overloaded, and also will make it
// easier to add versions that take predicates.
//
///////////////////

template <typename T>
index binary_search(const Array<T> & v, index lo, index hi, T key)
{
	// Invariant: If key is in v, then key is in [lo..hi).
	// When lo == hi, [lo..hi) is empty, and key is not found.
	while (hi > lo)
	{
		index mid = lo + (hi - lo) / 2;
		if (key < v[mid])
			hi = mid;
		else if (v[mid] < key)
			lo = mid + 1;
		else
			return mid;
	}

	return invalid;
}

template <typename T>
index binary_search(const Array<T> & v, T key)
{
	return binary_search(v, 0, v.size(), key);
}

template <typename T>
index lower_bound(const Array<T> & v, index lo, index hi, T key)
{
	// Invariant: [0..lo) < key, key <= [hi..v.size())
	// When lo == hi, v[hi] is the smallest index s.t. v[i] >= key
	while (lo != hi)
	{
		index mid = lo + (hi - lo) / 2;
		if (!(v[mid] < key))
			hi = mid;
		else
			lo = mid + 1;
	}

	return hi;
}

template <typename T>
index lower_bound(const Array<T> & v, T key)
{
	return lower_bound(v, 0U, v.size(), key);
}

template <typename T>
index upper_bound(const Array<T> & v, index lo, index hi, T key)
{
	// Invariant: [0..lo) <= key, key < [hi..v.size())
	// When lo == hi, v[hi] is the smallest index s.t. v[i] > key
	while (lo != hi)
	{
		index mid = lo + (hi - lo) / 2;
		if (key < v[mid])
			hi = mid;
		else
			lo = mid + 1;
	}

	return hi;
}

template <typename T>
index upper_bound(const Array<T> & v, T key)
{
	return upper_bound(v, 0U, v.size(), key);
}

template <typename T>
void copy(const Array<T> & src, index src_begin, index src_end,
		Array<T> & dst, index dest_begin)
{
	if (src_end > src.size())
		throw std::out_of_range("copy src");
	if (dest_begin + (src_end - src_begin) > dst.size())
		throw std::out_of_range("copy dst");
	for (index i = src_begin, j = dest_begin; i < src_end; i++, j++)
		dst[j] = src[i];
}

}

#endif
