
#pragma once

#include <functional>

#include "Array.h"
#include "StdArray.h"

namespace CSn
{

template<typename T, typename C = std::less<>>
void selection_sort(Array<T> & a, C cmp = C())
{
	for (index i = 0; i < a.size(); i++)
	{
		index min_idx = min_element(a, i, a.size(), cmp);
		swap(a, i, min_idx);
	}
}

template<typename T, typename C = std::less<>>
void insertion_sort(Array<T> & a, C cmp = C())
{
	for (index i = 1; i < a.size(); i++)
	{
		index idx = upper_bound(a, 0, i, a[i], cmp);
		rotate_right(a, idx, i+1);
	}
}

}
