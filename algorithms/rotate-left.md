## rotate_left(T[] a)

`rotate_left` rotates the entire array `a` one position to the left.
That is, `a[1]` is copied to `a[0]`, `a[2]` is copied to `a[1]`, etc.
`a[0]` is copied to `a[a.length-1]`.
```
rotate_left(T[] a)
    if a.length <= 1
        return
    T t = a[0]
    for (index i = 1; i < a.length; i++)
        a[i-1] = a[i]
    a[a.length-1] = t
```
While we do not need to treat an array
of size 1 as a special case, we do have
to treat a zero-length array specially,
so we may as well treat 1 specially also.

We could also do this with a series of swaps
and not have to use an extra variable.
Why is this method preferred?
