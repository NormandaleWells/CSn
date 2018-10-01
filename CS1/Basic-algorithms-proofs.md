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
with a half-open subrange of an array ```A```
will have this precondition:
```
assert 0 <= lo <= hi <= A.length
```
In discussions of postconditions,
I will use ```ret``` to represent
the return value of the function.

Some specific language implements have preconditions
that are not shown here,
since our pseudocode does not specify
whether arrays are reference or value
types.
For example, languages for which arrays
and array elements are reference types
may include these preconditions
(assume ```a``` is an array):
```
assert a != null
assert a[0,a.length) != null
```
Each specific basic algorithm is discussed
on its own page below.

* [`find(A, lo, hi, v)`](../algorithms-proof/find.md) - find first occurrence of `v` in `A[lo,hi)`
* [`count(A, lo, hi, v)`](../algorithms-proof/count.md) - count the number of items in `A[lo,hi)` equal to `v`
* [`min_element(A, lo, hi)`](../algorithms-proof/min-element.md) - find the minimum element of `A[lo,hi)`
* [`max_element(A, lo, hi)`](../algorithms-proof/max-element.md) - find the maximum element of `A[lo,hi)`
* [`rotate_left(A, lo, hi)`](../algorithms-proof/rotate-left.md) - rotate elements of `A[lo,hi)` one element to the left
* [`rotate_right(A, lo, hi)`](../algorithms-proof/rotate-right.md) - rotate elements of `A[lo,hi)` one element to the right
* [`copy(A, lo, hi, B, lo_dest)`](../algorithms-proof/copy.md) - copy `A[lo,hi)` to `B[lo_dest,lo_dest+(hi-lo))`
