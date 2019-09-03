## `index max_element(T[] a)`

`max_element` searches array `a` for the maximum element and returns its index.
If there are multiple occurrences of the maximum element in `a`, the index of the one with the lowest index is returned.
`a.length` must be at least 1; if not, `invalid` is returned.

Here is the full pseudocode for `max_element`:
```
index max_element(T[] a)
    if a.length = 0
        return invalid
    index max_index = 0
    for index i in [1,a.length)
        if a[i] > a[max_index]
            max_index = i
    return max_index
```
Why is it better to return the index of the maximum element, rather than its value?

What should we do with an empty array?

If there are multiple array entries with the minimum value,
how does this code ensure that lowest index is returned?
How could we change the code to return the highest index
containing the minimum value?

What are we assuming about the type `T`?
