## `swap(T[] a, index idx1, index idx2)`
`swap` exchanges the values in `a[idx1]` and `a[idx2]`.  No other elements of `a` are affected.

Here is the full pseudocode for `swap`:
```
swap(T[] a, index idx1, index idx2)
    T t = a[idx1]
    a[idx1] = a[idx2]
    a[idx2] = t
```
Note: If you search the web, you may find implementations of `swap()`
that avoid the use of a temporary variable
through some clever use of the `xor` function.
Those implementations are no faster than this one (and are often slower),
and only serve to obfuscate the code.
Don't use that trick.
