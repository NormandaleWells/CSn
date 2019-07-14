## `index find(T[] a, T v)`

`find` searches array `a` for the first element that matches a given value, and returns its index.
If there are multiple occurrences of `v` in `a`, the index of the one with the lowest index is returned.

If `v` is not found in `a`, the special value `invalid` is returned.

Here is the full pseudocode for `find`:
```
index find(T[] a, T v)
    for index i in [0,a.length)
        if a[i] = v
            return i
    return invalid
```
What happens if array `a` is empty?
