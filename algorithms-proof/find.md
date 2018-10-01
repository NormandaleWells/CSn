## `index find(A, lo, hi, v)`
```find``` has no special preconditions.
At first glance, it would appear that
the postcondition is simply that
one of the following is true:
* ret == invalid and a[lo,hi) != v
* ret != invalid and a[ret] = v

However, ```find```'s postcondition is slightly complicated
by the fact that we want to return the first element
equal to ```v```.
This means that no array element prior to ```ret```
may be equal to ```v```.
Therefore, the full postcondition is this:
```
assert (ret == invalid and a[lo,hi) != v)
        or (A[ret] == v and A[lo,ret) != v)
```
Note that because the postcondition is a disjunction,
and each of the two ```return``` statements
corresponds to one of two operands
of that disjunction,
the presence of two ```return``` statements
does not complicate the analysis.

The loop invariant is that
no element of the array
with an index less than ```i```
is equal to v:
```
assert A[lo,i) != v
```
For the first loop execution,
the loop invariant is trivially true,
since ```i == lo```.
(A universal quantifier applied to an empty
set is always true.)

Inside the loop,
after checking the condition ```A[i] = v```,
one of two things is true.
* ```A[i] == v``` which, combined with the loop invariant
and the return statement,
establishes that ```A[lo,i) != v and A[i] == v```,
which is exactly one operand of the disjunction
in the postcondition.
* ```A[i] != v``` which, combined with the loop invariant,
means that ```A[lo,i] != v``` (note the closed range).
After ```i``` is incremented at the end of the loop,
we have ```A[lo,i) != v```, and therefore the loop
invariant is re-established.

If we exit the loop, we have ```i == hi```,
and so our invariant becomes ```a[lo,hi) != v```,
which, combined with the return statement,
establishes one of the operands
in the postcondition's disjunction.

The fully annotated code for ```find``` is therefore: 
```
index find(A, lo, hi, v)
    assert 0 <= lo <= hi <= A.length
    for i in [lo,hi)
        assert A[lo,i) != v
        if A[i] = v
            assert A[lo,i) != v and A[i] == v
            return i
    assert A[lo,hi) != v
    return invalid
```
