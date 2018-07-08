## `index min_element(A, lo, hi)`
`min_element` searches the specified sub-range of array `A` for the minimum element and returns its index.
If there are multiple occurrences of the minimum element in `A[lo,hi)`,
the index of the one with the lowest index is returned.
The sub-range must not be empty; if it is, `invalid` is returned.

Here is the full pseudocode for `min_element`:
```
index min_element(A, lo, hi)
    if hi - lo = 0
        return invalid
    index min_index = lo
    for i in [lo+1,hi)
        if A[i] < A[min_index]
            min_index = i
    return min_index
```
What should `min_element()` do with a 1-element sub-range?  How does this implementation handle that case?
