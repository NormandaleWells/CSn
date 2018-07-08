## `index min_element(A)`
`min_element` searches array `A` for the minimum element and returns its index.
If there are multiple occurrences of the minimum element in `A`, the index of the one with the lowest index is returned.
`A.length` must be at least 1; if not, `invalid` is returned.

Here is the full pseudocode for `min_element`:
```
index min_element(A)
    if A.length = 0
        return invalid
    index min_index = 0
    for i in [1,A.length)
        if A[i] < A[min_index]
            min_index = i
    return min_index
```
Why is it better to return the index of the minimum element, rather than its value?
