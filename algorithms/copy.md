## `copy(T[] a, T[] b)`

`copy` copies array `a` to array `b`.  The number of elements copied is the
minimum of the lengths of the two arrays.

Here is the full pseudocode for `copy`:
```
copy(T[] a, T[] b)
    for index i in [0,min(a.length,b.length))
        b[i] = a[i]
```
What are we assuming about the length of array `b`?

NOTE: In a reference-based,
garbage-collected language like Java,
it may be advantageous to
also have a similar `move()` algorithm
which not only does a copy
but also sets `a[i]` to null.
This avoids loitering - that is,
keeping a reference to memory that
is no longer needed.

In C++, it would be advantageous
to have a `move()` algorithm that
uses move semantics instead of copy
semantics.
