
#pragma once

#include <functional>

#include "Array.h"
#include "StdArray.h"

namespace CSn
{

template<typename T, typename C = std::less<>>
void selection_sort(Array<T> & a, index lo, index hi, C cmp = C())
{
	for (index i = lo; i < hi; i++)
	{
		index min_idx = min_element(a, i, hi, cmp);
		swap(a, i, min_idx);
	}
}

template<typename T, typename C = std::less<>>
void selection_sort(Array<T> & a, C cmp = C())
{
	selection_sort(a, 0, a.size(), cmp);
}

template<typename T, typename C = std::less<>>
void insertion_sort(Array<T> & a, index lo, index hi, C cmp = C())
{
	for (index i = lo + 1; i < hi; i++)
	{
		index idx = upper_bound(a, lo, i, a[i], cmp);
		rotate_right(a, idx, i + 1);
	}
}

template<typename T, typename C = std::less<>>
void insertion_sort(Array<T> & a, C cmp = C())
{
	insertion_sort(a, 0, a.size(), cmp);
}

}
