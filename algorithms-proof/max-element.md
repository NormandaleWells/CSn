## `index max_element(A, lo, hi)`
The first item to note in this analysis
is that ```max_element``` does not quite
share the standard precondition that ```0 <= lo <= hi <= A.length```,
since that allows for an empty subrange.

For max_element,
we need to have at least element in the array
in order for it to be meaningful
to have a maximum element.
Therefore, the precondition here is
```
Pre: 0 <= lo < hi <= A.length
```
It is possible for the maximum element
to appear multiple times in the array.
```max_element``` is defined as returning
the first such maximum element.
Therefore,
the postcondition must reflect the fact that
all elements before the one at the returned index
are less than the maximum element,
and all elements after that one are
greater than or equal to the maximum element.

So the postcondition is:
```
Post: A[lo,ret) < A[ret] and A[ret,hi) <= A[ret]
```
As usual, the loop invariant
mimics the postcondition.
At the start of the loop,
we want to ensure that:
```
Inv: A[lo,max_index) < A[max_index] and A[max_index,i) <= A[max_index]
```
Within the loop, we check ```A[i] > A[max_index]```,
and so we have two possibilities:
* ```A[i] > A[max_index] >= A[lo,i)```,
and so after assignment ```i``` to ```max_index```,
we have ```A[max_index] >= A[lo,i]```.
* ```A[i] <= A[max_index]```,
and so ```A[max_index,i] <= A[max_index]```.

Either way, after ```i``` is incremented
at the end of the loop,
the closed ranges above become half-open ranges,
and the loop invariant is re-established.

The fully annotated code for ```max_element``` is:
```
index max_element(A, lo, hi)
    Pre: 0 <= lo < hi <= a.length
    if hi - lo = 0
        error "call to max_element with empty subrange"
    index max_index = lo
    for i in [lo+1,hi)
        Inv: a[lo,max_index) < a[max_index] and a[max_index,i) <= a[max_index]
        if A[i] > A[max_index]
            max_index = i
        Inv: a[lo,max_index) < a[max_index] and a[max_index,i] <= a[max_index]
    Post: a[lo,max_index) < a[max_index] and a[max_index,hi) <= a[max_index]
    return max_index
```
