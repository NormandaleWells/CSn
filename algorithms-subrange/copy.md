## `copy(A, lo, hi, B, lo_dest)`
`copy` copies `A[lo,hi)` to `B[lo_dest,lo_dest+(hi-lo)`.

Note that there is no need to specify an upper bound of the range we're copying to, since it can be calculated.

We assume for now that `0 <= lo <= hi <= A.length`, and `0 <= lo_dest <= lo_dest+(hi-lo) <= B.length`.  If this
condition is violated, the runtime system will probably do something...bad.  Later on (when we discuss preconditions),
we'll handle out-of-bound indices explicitly.

Here is the full pseudocode for `copy`:
```
copy(A, lo, hi, B, lo_dest)
    for i in [lo,hi)
        B[lo_dest+(i-lo)] = A[i]
```
