## rotate_left(A)

`rotate_left` rotates the entire array `A` one position to the left.
That is, `A[1]` is copied to `A[0]`, `A[2]` is copied to `A[1]`, etc.
`A[0]` is copied to `A[A.length-1]`.
```
rotate_left(A)
    if A.length <= 1
        return
    A.type t = A[0]
    for (index i = 1; i < A.length; i++)
        A[i-1] = A[i]
    A[A.length-1] = t
```
While we do not need to treat an array
of size 1 as a special case, we do have
to treat a zero-length array specially,
so we may as well treat 1 specially also.
