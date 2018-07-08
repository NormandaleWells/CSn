## `index max_element(A, lo, hi)`
`max_element` searches the specified sub-range of array `A` for the maximum element and returns its index.
If there are multiple occurrences of the maximum element in `A[lo,hi)`,
the index of the one with the lowest index is returned.
The sub-range must not be empty; if it is, `invalid` is returned.

Here is the full pseudocode for `max_element`:
```
index max_element(A, lo, hi)
    if hi - lo = 0
        return invalid
    index max_index = lo
    for i in [lo+1,hi)
        if A[i] > A[max_index]
            max_index = i
    return max_index
```
What should `max_element()` do with a 1-element sub-range?  How does this implementation handle that case?
