## rotate_left(T[] a, index lo, index hi)

`rotate_left` rotates the specified sub-range
of array `a` one position to the left.
That is, `a[lo+1]` is copied to `a[lo]`,
`a[lo+2]` is copied to `a[lo+1]`, etc.
`a[lo]` is copied to `a[hi-1]`.
```
rotate_left(T[] a, index lo, index hi)
    if hi-lo <= 1
        return
    a.type t = a[lo]
    for (index i = lo+1; i < hi; i++)
        a[i-1] = a[i]
    a[hi-1] = t
```

What should happen with sub-ranges
of size 0, 1, and 2?
How does this implementation
handle those cases?
