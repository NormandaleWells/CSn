## `index find(A, v)`
`find` searches array `A` for the first element that matches a given value, and returns its index.
`v` must be an object of the same type as those stored in `A`.
If there are multiple occurrences of `v` in `A`, the index of the one with the lowest index is returned.

If `v` is not found in `A`, the special value `invalid` is returned.

Here is the full pseudocode for `find`:
```
index find(A, v)
    for i in [0,A.length)
        if A[i] = v
            return i
    return invalid
```
