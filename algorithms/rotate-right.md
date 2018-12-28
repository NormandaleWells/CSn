## rotate_right(A)

`rotate_right` rotates the entire array `A` one position to the right.
That is, `A[0]` is copied to `A[1]`, `A[1]` is copied to `A[2]`, etc.
`A[A.length-1]` is copied to `A[0]`.
```
rotate_right(A)
    if a.length <= 1
        return
    A.type t = A[A.length-1]
    for (index i = A.length-1; i > 0; i--)
        A[i] = A[i-1]
    A[0] = t
```
While we do not need to treat an array of size 1 as a special case, we do have to treat a zero-length array specially, so we may as well treat 1 specially also.

Why do we need to go through the array backwards?
