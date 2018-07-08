## `integer count(A, lo, hi, v)`
`count` returns a count of the number of elements in the specified sub-range of array `A` that match `v`.  `v` must be the same type of object as is stored in `A`.

Here is the full pseudocode for `count`:
```
index count(A, v)
    integer count = 0;
    for i in [lo,hi)
        if A[i] = v
            count = count + 1
    return count
```
What should `count()` return if the sub-range is empty?  How does this implementation handle that case?
