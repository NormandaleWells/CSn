## `integer count(A, v)`
`count` returns a count of the number of elements of array `A` that match `v`.
`v` must be the same type of object as is stored in `A`.

Here is the full pseudocode for `count`:
```
index count(A, v)
    integer count = 0;
    for i in [0,A.length)
        if A[i] = v
            count = count + 1
    return count
```
What should `count()` return if the array is empty?  How does this implementation handle that case?
