## `integer count(T[] a, T v)`

`count` returns a count of the number of elements of array `a` that match `v`.

Here is the full pseudocode for `count`:
```
index count(T[] a, T v)
    integer count = 0;
    for index i in [0,a.length)
        if a[i] = v
            count = count + 1
    return count
```
What should `count()` return if the array is empty?  How does this implementation handle that case?
