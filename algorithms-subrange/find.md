## `index find(A, lo, hi, v)`
`find` searches the specified sub-range of array `A` for the first element that matches a given value, and returns its index.
`v` must be an object of the same type as those stored in `A`.
If there are multiple occurrences of `v` in `A[lo:hi)`, the index of the one with the lowest index is returned.

If `v` is not found in `A[lo:hi)`, the special value `invalid` is returned.

Here is the full pseudocode for `find`:
```
index find(A, lo, hi, v)
    for i in [lo,hi)
        if A[i] = v
            return i
    return invalid
```
