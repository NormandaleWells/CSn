## rotate_right(T[] a, index lo, index hi)

`rotate_right` rotates the specified sub-range
of array `a` one position to the right.
That is, `a[lo]` is copied to `a[lo+1]`,
`a[lo+1]` is copied to `a[lo+2]`, etc.
`a[hi-1]` is copied to `a[lo]`.
```
rotate_right(T[] a, index lo, index hi)
    if hi-lo <= 1
        return
    a.type t = a[hi-1]
    for (index i = hi-1; i > lo; i--)
        a[i] = a[i-1]
    a[lo] = t
```
Why do we need to go through the array backwards?
	
What should happen with sub-ranges
of size 0, 1, and 2?
How does this implementation
handle those cases?
