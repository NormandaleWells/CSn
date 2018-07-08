## rotate_right(A, lo, hi)

`rotate_right` rotates the specified sub-range of array `A` one position to the right.
That is, `A[lo]` is copied to `A[lo+1]`, `A[lo+1]` is copied to `A[lo+2]`, etc.
`A[hi-1]` is copied to `A[lo]`.
```
rotate_right(A, lo, hi)
    if hi-lo <= 1
        return
    A.type t = A[hi-1]
    for (index i = hi-1; i > 0; i--)
        A[i] = A[i-1]
    A[lo] = t
```
Why do we need to go through the array backwards?

What should happen with an sub-range of sizes 0, 1, and 2?  How does this implementation handle those cases?
