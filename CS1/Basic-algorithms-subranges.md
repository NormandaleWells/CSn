## Basic algorithms with sub-ranges

In these section, we're going to modify our [basic algorithms](Basic-algorithms.md)
to support sub-ranges.
We'll change the calling parameters so that index variables `lo` and `hi`
come immediately after the array,
followed by other parameters (if any).
For example, the find algorithm becomes
```
index find(T[] a, index lo, index hi, v)
```
For convenience, comments will use `a[lo:hi)` as a shortcut
for "all the elements of a starting with `lo`
up to but no including `hi`".

For now, we will assume that `0 <= lo <= hi <= a.length`,
and let the underlying language signal an error if not.
Later on, we'll handle these errors explicitly - see the section on preconditions.

We'll keep the original versions for convenience,
but they'll be modified to simply call the version that takes a sub-range,
passing `0` for `lo` and `a.length` for `hi`, like this:
```
index find(T[] a, v)
    return find(a, 0, a.length, v)
```
Here are the new calling parameters:
* [`find(T[] a, index lo, index hi, T v)`](../algorithms-subrange/find.md) - find first occurrence of `v` in `a[lo,hi)`
* [`count(T[] a, index lo, index hi, T v)`](../algorithms-subrange/count.md) - count the number of items in `a[lo,hi)` equal to `v`
* [`min_element(T[] a, index lo, index hi)`](../algorithms-subrange/min-element.md) - find the minimum element of `a[lo,hi)`
* [`max_element(T[] a, index lo, index hi)`](../algorithms-subrange/max-element.md) - find the maximum element of `a[lo,hi)`
* [`rotate_left(T[] a, index lo, index hi)`](../algorithms-subrange/rotate-left.md) - rotate elements of `a[lo,hi)` one element to the left
* [`rotate_right(T[] a, index lo, index hi)`](../algorithms-subrange/rotate-right.md) - rotate elements of `a[lo,hi)` one element to the right
* [`copy(T[] a, index lo, index hi, T[] b, index lo_dest)`](../algorithms-subrange/copy.md) - copy `a[lo,hi)` to `b[lo_dest,lo_dest+(hi-lo))`

Note that we don't create a sub-range version of swap,
since swap does not operate on the entire array;
it just modifies the elements at two specific indices.

Note also that copy() does not necessarily copy from a
to the corresponding range in b;
instead, we specify the start of a range to copy to.
There is no need to specify the end of the range,
since it can be calculated.
