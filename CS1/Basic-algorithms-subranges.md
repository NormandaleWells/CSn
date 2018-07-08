In these section, we're going to modify our [basic algorithms](Basic-algorithms.md) to support sub-ranges.
We'll change the calling parameters so that index variables `lo` and `hi` come immediately after the array,
followed by other parameters (if any).  For example, the find algorithm becomes
```
index find(A, lo, hi, v)
```
For convenience, comments will use `A[lo:hi)` as a shortcut for "all the elements of A starting with `lo` up to but no including `hi`".

We'll keep the original versions for convenience, but they'll be modified to simply call the version that takes a sub-range,
passing `0` for `lo` and `A.length` for `hi`, like this:
```
index find(A, v)
    return find(A, 0, A.length, v)
```
Here are the new calling parameters:
* [`find(A, lo, hi, v)`](../algorithms-subrange/find.md) - find first occurrence of `v` in `A[lo:hi)`
* [`count(A, lo, hi, v)`](../algorithms-subrange/count.md) - count the number of items in `A[lo:hi)` equal to `v`
* [`min_element(A, lo, hi)`](../algorithms-subrange/min-element.md) - find the minimum element of `A[lo:hi)`
* [`max_element(A, lo, hi)`](../algorithms-subrange/max-element.md) - find the maximum element of `A[lo:hi)`
* [`rotate_left(A, lo, hi)`](../algorithms-subrange/rotate-left.md) - rotate elements of `A[lo:hi)` one element to the left
* [`rotate_right(A, lo, hi)`](../algorithms-subrange/rotate-right.md) - rotate elements of `A[lo:hi)` one element to the right

Note that we don't create a sub-range version of swap, since swap does not operate on the entire array; it just modifies the elements at two specific indices.
