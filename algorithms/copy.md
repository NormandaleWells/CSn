## `copy(A, B)`
`copy` copies array `A` to array `B`.  The number of elements copied is the
minimum of the lengths of the two arrays.

Here is the full pseudocode for `copy`:
```
find(A, B)
    for i in [0,min(A.length,B.length))
        B[i] = A[i]
```
