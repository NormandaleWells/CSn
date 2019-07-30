## `copy(T[] a, index lo, index hi, T[] b, index lo_dest)`
`copy` copies `a[lo,hi)` to `b[lo_dest,lo_dest+(hi-lo)`.

Note that there is no need
to specify an upper bound
of the range we're copying to,
since it can be calculated.

We assume for now that `0 <= lo <= hi <= a.length`,
and `0 <= lo_dest <= lo_dest+(hi-lo) <= b.length`.
If this condition is violated,
the runtime system will probably do something...bad.
Later on (when we discuss [preconditions](../CS1/Preconditions.md)),
we'll handle out-of-bound indices explicitly.

Here is the full pseudocode for `copy`:
```
copy(T[] a, index lo, index hi, T[] b, index lo_dest)
    for index in [lo,hi)
        b[lo_dest+(i-lo)] = a[i]
```

Why might it be useful to specify
`lo_dest` rather than just copying
starting at `lo` in both arrays?
Spoiler: see the implementation
of the Queue ADT with a
resizeable array.
