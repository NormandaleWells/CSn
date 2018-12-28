## rotate_left(A, lo, hi)

`rotate_left` rotates the specified sub-range of array `A` one position to the left.
That is, `A[lo+1]` is copied to `A[lo]`, `A[lo+2]` is copied to `A[lo+1]`, etc.
`A[lo]` is copied to `A[hi-1]`.
```
rotate_left(A, lo, hi)
    if hi-lo <= 1
        return
    A.type t = A[lo]
    for (index i = lo+1; i < hi; i++)
        A[i-1] = A[i]
    A[hi-1] = t
```
