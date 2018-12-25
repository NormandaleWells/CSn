
#ifndef ALGS4_STDARRAY_H
#define ALGS4_STDARRAY_H

#include "Array.h"

#include <stdexcept>
#include <utility>
#include <vector>

namespace CSn
{

template <typename T>
size_t find(const Array<T> & v, size_t lo, size_t hi, T t)
{
	for (size_t i = lo; i < hi; i++)
	{
		if (v[i] == t) return i;
	}

	return hi;
}

template <typename T>
size_t find(const Array<T> & v, T t)
{
	return find(v, 0U, v.size(), t);
}

template <typename T, typename Pred>
size_t find(const Array<T> & v, size_t lo, size_t hi, Pred p)
{
	for (size_t i = lo; i < hi; i++)
	{
		if (p(v[i])) return i;
	}

	return hi;
}

template <typename T, typename Pred>
size_t find(const Array<T> & v, Pred p)
{
	return find(v, 0U, v.size(), p);
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
size_t binary_search(const Array<T> & v, size_t lo, size_t hi, T key)
{
	// Invariant: If key is in v, then key is in [lo..hi).
	// When lo == hi, [lo..hi) is empty, and key is not found.
	while (hi > lo)
	{
		size_t mid = lo + (hi - lo) / 2;
		if (key < v[mid])
			hi = mid;
		else if (v[mid] < key)
			lo = mid + 1;
		else
			return mid;
	}

	return -1;
}

template <typename T>
size_t binary_search(const Array<T> & v, T key)
{
	return binary_search(v, 0U, v.size(), key);
}

template <typename T>
size_t lower_bound(const Array<T> & v, size_t lo, size_t hi, T key)
{
	// Invariant: [0..lo) < key, key <= [hi..v.size())
	// When lo == hi, v[hi] is the smallest index s.t. v[i] >= key
	while (lo != hi)
	{
		size_t mid = lo + (hi - lo) / 2;
		if (!(v[mid] < key))
			hi = mid;
		else
			lo = mid + 1;
	}

	return hi;
}

template <typename T>
size_t lower_bound(const Array<T> & v, T key)
{
	return lower_bound(v, 0U, v.size(), key);
}

template <typename T>
size_t upper_bound(const Array<T> & v, size_t lo, size_t hi, T key)
{
	// Invariant: [0..lo) <= key, key < [hi..v.size())
	// When lo == hi, v[hi] is the smallest index s.t. v[i] > key
	while (lo != hi)
	{
		size_t mid = lo + (hi - lo) / 2;
		if (key < v[mid])
			hi = mid;
		else
			lo = mid + 1;
	}

	return hi;
}

template <typename T>
size_t upper_bound(const Array<T> & v, T key)
{
	return upper_bound(v, 0U, v.size(), key);
}

template <typename T>
void copy(const Array<T> & src, size_t src_begin, size_t src_end, Array<T> & dst, size_t dest_begin)
{
	if (src_end > src.size())
		throw std::out_of_range("copy src");
	if (dest_begin + (src_end - src_begin) > dst.size())
		throw std::out_of_range("copy dst");
	for (size_t i = src_begin, j = dest_begin; i < src_end; i++, j++)
		dst[j] = src[i];
}

}

#endif
