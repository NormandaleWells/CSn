
#pragma once

#include "Array.h"

#include <cassert>
#include <functional>
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

template <typename T, typename C = std::greater<>>
index max_element(const Array<T> & v, index lo, index hi, C cmp = C())
{
	assert(0 <= lo);
	assert(lo < hi);
	assert(hi <= v.size());

	index idx_max = lo;
	for (index i = lo; i < hi; i++)
		if (cmp(v[i], v[idx_max]))
			idx_max = i;
	return idx_max;
}

template <typename T, typename C = std::greater<>>
index max_element(const Array<T> & v, C cmp = C())
{
	return max_element(v, 0, v.size(), cmp);
}

template <typename T, typename C = std::less<>>
index min_element(const Array<T> & v, index lo, index hi, C cmp = C())
{
	assert(0 <= lo);
	assert(lo < hi);
	assert(hi <= v.size());

	index idx_min = lo;
	for (index i = lo; i < hi; i++)
		if (cmp(v[i], v[idx_min]))
			idx_min = i;
	return idx_min;
}

template <typename T, typename C = std::less<>>
index min_element(const Array<T> & v, C cmp = C())
{
	return min_element(v, 0, v.size(), cmp);
}

template <typename T>
void rotate_left(Array<T> & a, index lo, index hi)
{
	assert(0 <= lo);
	assert(lo <= hi);
	assert(hi <= a.size());

	if (hi - lo <= 1)
		return;
	T t = a[lo];
	for (index i = lo + 1; i < hi; i++)
		a[i - 1] = a[i];
	a[hi - 1] = t;
}

template <typename T>
void rotate_left(Array<T> & a)
{
	rotate_left(a, 0, a.size());
}

template <typename T>
void rotate_right(Array<T> & a, index lo, index hi)
{
	assert(0 <= lo);
	assert(lo <= hi);
	assert(hi <= a.size());

	if (hi - lo <= 1)
		return;
	T t = a[hi-1];
	for (index i = hi - 1; i > lo; i--)
		a[i] = a[i - 1];
	a[lo] = t;
}

template <typename T>
void rotate_right(Array<T> & a)
{
	rotate_right(a, 0, a.size());
}

template <typename T>
void swap(Array<T> & a, index idx1, index idx2)
{
	assert(0 <= idx1);
	assert(0 <= idx2);
	assert(idx1 < a.size());
	assert(idx2 < a.size());

	T t = a[idx1];
	a[idx1] = a[idx2];
	a[idx2] = t;
}

template <typename T, typename C = std::less<>>
index binary_search(const Array<T> & v, index lo, index hi, T key, C cmp = C())
{
	// Invariant: If key is in v, then key is in [lo..hi).
	// When lo == hi, [lo..hi) is empty, and key is not found.
	while (hi > lo)
	{
		index mid = lo + (hi - lo) / 2;
		if (cmp(key, v[mid]))
			hi = mid;
		else if (cmp(v[mid], key))
			lo = mid + 1;
		else
			return mid;
	}

	return invalid;
}

template <typename T, typename C = std::less<>>
index binary_search(const Array<T> & v, T key, C cmp = C())
{
	return binary_search(v, 0, v.size(), key, cmp);
}

template <typename T, typename C = std::less<>>
index lower_bound(const Array<T> & v, index lo, index hi, T key, C cmp = C())
{
	// Invariant: [0..lo) < key, key <= [hi..v.size())
	// When lo == hi, v[hi] is the smallest index s.t. v[i] >= key
	while (lo != hi)
	{
		index mid = lo + (hi - lo) / 2;
		if (cmp(v[mid], key))
			lo = mid + 1;
		else
			hi = mid;
	}

	return hi;
}

template <typename T, typename C = std::less<>>
index lower_bound(const Array<T> & v, T key, C cmp = C())
{
	return lower_bound(v, 0U, v.size(), key, cmp);
}

template <typename T, typename C = std::less<>>
index upper_bound(const Array<T> & v, index lo, index hi, T key, C cmp = C())
{
	// Invariant: [0..lo) <= key, key < [hi..v.size())
	// When lo == hi, v[hi] is the smallest index s.t. v[i] > key
	while (lo != hi)
	{
		index mid = lo + (hi - lo) / 2;
		// Note that v[mid] <= key <-> key >= v[mid] <-> !(key < v[mid])
		if (!cmp(key, v[mid]))
			lo = mid + 1;
		else
			hi = mid;
	}

	return hi;
}

template <typename T, typename C = std::less<>>
index upper_bound(const Array<T> & v, T key, C cmp = C())
{
	return upper_bound(v, 0U, v.size(), key, cmp);
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
