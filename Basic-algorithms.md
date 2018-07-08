This section introduces some very basic array-based algorithms.  We'll see that we can put these simple algorithms to use in creating more complex algorithms and data structures.  Also, as we go through the topics in CS1, we'll come back to these often and update them to reflect what we've learned.

We're purposely ignoring the fact that most of these algorithms are available in the standard libraries of most languages, since the purpose here is to show how they're implemented, and how to use them to get useful work done.  They're also simple enough to provide good examples when discussing preconditions, loop invariants, and generic coding.  When writing in, say, C++, you're advised to use the versions in the `<algorithms>` header rather than these.

The algorithms we'll be studying are:
* [`find(A, v)`](find-algorithm.md) - find first occurrence of `v` in `A`
* [`count(A, v)`](count-algorithm.md) - count the number of items in `A` equal to `v`
* [`min_element(A)`](min-element-algorithm.md) - find the minimum element of `A`
* [`max_element(A)`](max-element-algorithm.md) - find the maximum element of `A`
* [`swap(A, idx1, idx2)`](swap-algorithm.md) - exchange `A[idx1]` and `A[idx2]`
* [`rotate_left(A)`](rotate-left-algorithm.md) - rotate elements of `A` one element to the left
* [`rotate_right(A)`](rotate-right-algorithm.md) - rotate elements of `A` one element to the right

Our initial versions will work only with arrays of integers; we'll create generic versions later on.

In later sections, we'll add these others:
* `is_sorted(A)` - determine if array `A` is sorted (ascending)
* `selection_sort(A)` - sort array `A` into ascending order
* `insertion_sort(A)` - sort array `A` into ascending order
* `lower_bound(A, v)` - find the first array element in `A` greater than or equal to `v`
* `upper_bound(A, v)` - find the first array element in `A` strictly greater than `v`

We'll revisit these basic algorithms often, to do such things as:
* Implement versions that operate on a subrange of the array
* Create generic versions
* Document preconditions
* Document loop invariants
* Work with other conditions (predicates) and comparisons

Finally, these algorithms may be used as homework assignments:
* `any_of(A, v)` - return `true` if 'A' contains at least one occurrence of `v`, `false` otherwise
* `all_of(A, v)` - return `true` if every element of `A` is equal to `v`, false otherwise
* `none_of(A, v)` - return `true` if no element of `A` is equal to `v`, false otherwise
* `fill(A, v)` - fill `A` with copies of `v`
* `accumulate(A)` - add all the elements of `A` and return the result
* `reverse(A)` - reverse the elements of `A`
* `rotate(A, lo, mid, hi)` - rotate elements of `A[lo,hi)` so that `A[mid]` moves to `A[lo]`

Note that among the first three (`any_of()`, `all_of()`, and `none_of()`), two are exact opposites of each other; that is, any inputs for which one returns `true`, the other will return `false`, and vice versa.  And they are not the two you might first think!
