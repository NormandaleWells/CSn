## `index find(T[] a, index lo, index hi, T v)`
`find` has no special preconditions.
At first glance, it would appear that
the postcondition is simply that
one of the following is true:
* ret == invalid and a[lo,hi) != v
* ret != invalid and a[ret] = v

However, `find`'s postcondition is slightly complicated
by the fact that we want to return the first element
equal to `v`.
This means that no array element prior to `ret`
may be equal to `v`.
Therefore, the full postcondition is this:
```
Post: (ret == invalid and a[lo,hi) != v)
        or (a[ret] == v and a[lo,ret) != v)
```
Note that because the postcondition is a disjunction,
and each of the two `return` statements
corresponds to one of two operands
of that disjunction,
the presence of two `return` statements
does not complicate the analysis.

The loop invariant is that
no element of the array
with an index less than `i`
is equal to v:
```
Inv: a[lo,i) != v
```
For the first loop execution,
the loop invariant is trivially true,
since `i == lo`.
(A universal quantifier applied to an empty
set is always true.)

Inside the loop,
after checking the condition `a[i] = v`,
one of two things is true.
* `a[i] == v` which, combined with the loop invariant
and the return statement,
establishes that `a[lo,i) != v and a[i] == v`,
which is exactly one part of the disjunction
in the postcondition.
* `a[i] != v` which, combined with the loop invariant,
means that `a[lo,i] != v` (note the closed range).
After `i` is incremented at the end of the loop,
we have `a[lo,i) != v`, and therefore the loop
invariant is re-established.

If we exit the loop, we have `i == hi`,
and so our invariant becomes `a[lo,hi) != v`,
which, combined with the return statement,
establishes the other part
of the postcondition's disjunction.

The fully annotated code for `find` is therefore: 
```
index find(T[] a, index lo, index hi, T v)
    Pre: 0 <= lo <= hi <= a.length
    for i in [lo,hi)
        Inv: a[lo,i) != v
        if a[i] = v
            Post: a[lo,i) != v and a[i] == v
            return i
        Inv: a[lo,hi] != v
    Post: a[lo,hi) != v
    return invalid
```
