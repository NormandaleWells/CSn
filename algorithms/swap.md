## `swap(A, idx1, idx2)`
`swap` exchanges the values in `A[idx1]` and `A[idx2]`.  No other elements of `A` are affected.

Here is the full pseudocode for `swap`:
```
swap(A, idx1, idx2)
    A.type t
    t = A[idx1]
    A[idx1] = A[idx2]
    A[idx2] = t
```
Note: If you search the web, you may find implementations of `swap()` that avoid the use of a temporary variable
through some clever use of the `xor` function.
Those implementations are no faster (and are often slower), and actually just obfuscate the code.
Don't use that trick.
