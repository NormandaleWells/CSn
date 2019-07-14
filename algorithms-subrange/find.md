## `index find(T[] a, index lo, index hi, T v)`
`find` searches the specified sub-range
of array `a` for the first element
that matches the given value,
and returns its index.
If there are multiple occurrences
of `v` in `a[lo,hi)`,
the index of the one
with the lowest index is returned.

If `v` is not found in `a[lo,hi)`, the special value `invalid` is returned.

Here is the full pseudocode for `find`:
```
index find(T[] a, index lo, index hi, T v)
    for index i in [lo,hi)
        if a[i] = v
            return i
    return invalid
```
What should `find()` return
if the sub-range is empty?
How does this implementation
handle that case?
