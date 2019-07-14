## `integer count(T[] a, index lo, index hi, T v)`
`count` returns a count
of the number of elements
in the specified sub-range of array `a`
that match `v`.

Here is the full pseudocode for `count`:
```
integer count(T[] a, index lo, index hi, T v)
    integer count = 0;
    for index i in [lo,hi)
        if a[i] = v
            count = count + 1
    return count
```
What should `count()` return
if the sub-range is empty?
How does this implementation
handle that case?
