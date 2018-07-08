## `index max_element(A)`
`max_element` searches array `A` for the maximum element and returns its index.
If there are multiple occurrences of the maximum element in `A`, the index of the one with the lowest index is returned.
`A.length` must be at least 1; if not, `invalid` is returned.

Here is the full pseudocode for `max_element`:
```
index max_element(A)
    if A.length = 0
        return invalid
    index max_index = 0
    for i in [1,A.length)
        if A[i] > A[max_index]
            max_index = i
    return max_index
```
Why is it better to return the index of the maximum element, rather than its value?
