## `index min_element(T[] a)`

`min_element` searches array `a` for the minimum element and returns its index.
If there are multiple occurrences of the minimum element in `a`, the index of the one with the lowest index is returned.
`a.length` must be at least 1; if not, `invalid` is returned.

Here is the full pseudocode for `min_element`:
```
index min_element(T[] a)
    if a.length = 0
        return invalid
    index min_index = 0
    for index i in [1,a.length)
        if a[i] < a[min_index]
            min_index = i
    return min_index
```
Why is it better to return the index of the minimum element, rather than its value?

What should we do with an empty array?

What are we assuming about the type `T`?
