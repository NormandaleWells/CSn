## rotate_right(T[[] a)

`rotate_right` rotates the entire array `a` one position to the right.
That is, `a[0]` is copied to `a[1]`, `a[1]` is copied to `a[2]`, etc.
`a[a.length-1]` is copied to `a[0]`.
```
rotate_right(a)
    if a.length <= 1
        return
    T t = a[a.length-1]
    for (index i = a.length-1; i > 0; i--)
        a[i] = a[i-1]
    a[0] = t
```
While we do not need to treat an array
of size 1 as a special case,
we do have to treat a zero-length array specially,
so we may as well treat 1 specially also.

Why do we need to go through the array backwards?

We could also do this with a series of swaps
and not have to use an extra variable.
Why is this method preferred?
