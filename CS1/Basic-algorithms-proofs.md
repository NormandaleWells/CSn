## Proofs of basic algorithms

In this section,
we will revisit some of our basic array functions
and apply the concepts of preconditions,
postconditions, and invariants to create
informal proofs of correctness.
We'll see that the key to understanding these proofs
is to see how the loop invariant naturally leads
to the postconditions.

Any algorithms which work
with a half-open subrange of an array `a`
will have this precondition:
```
Pre: 0 <= lo <= hi <= a.length
```
In discussions of postconditions,
I will use `ret` to represent
the return value of the function.

Some specific language implements have preconditions
that are not shown here,
since our pseudocode does not specify
whether arrays are reference or value
types.
For example, languages for which arrays
and array elements are reference types
may include these preconditions
(assume `a` is an array):
```
Pre: a != null
Pre: a[lo,hi) != null
```

Remember that throughout this section,
we use a shortcut notation `a[lo,hi)`
to mean "all the elements of a in the
half-open range `[lo,hi)`.
For example, `a[lo,hi) = 0`
is a shortcut for "all elements of a
in the range `[lo,hi)` are equal to 0".

Each specific basic algorithm is discussed
on its own page below.

* [`find(T[] a, index index lo, hi, T v)`](../algorithms-proof/find.md) - find first occurrence of `v` in `a[lo,hi)`
* [`count(T[] a, index lo, index hi, T v)`](../algorithms-proof/count.md) - count the number of items in `a[lo,hi)` equal to `v`
* [`min_element(T[] a, index lo, index hi)`](../algorithms-proof/min-element.md) - find the minimum element of `a[lo,hi)`
* [`max_element(T[] a, index lo, index hi)`](../algorithms-proof/max-element.md) - find the maximum element of `a[lo,hi)`
* [`rotate_left(T[] a, index lo, index hi)`](../algorithms-proof/rotate-left.md) - rotate elements of `a[lo,hi)` one element to the left
* [`rotate_right(T[] a, index lo, index hi)`](../algorithms-proof/rotate-right.md) - rotate elements of `a[lo,hi)` one element to the right
* [`copy(T[] a, index lo, index hi, T[] b, index lo_dest)`](../algorithms-proof/copy.md) - copy `a[lo,hi)` to `b[lo_dest,lo_dest+(hi-lo))`
