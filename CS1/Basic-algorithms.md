## Basic algorithms

This section introduces some very basic array-based algorithms.
We'll see that we can put these simple algorithms to use in creating
more complex algorithms and data structures.
Also, as we go through the topics in CS1, we'll come back to these often
and update them to reflect what we've learned.

We're purposely ignoring the fact that most of these algorithms are available
in the standard libraries of most languages,
since the purpose here is to show how they're implemented,
and how to use them to get useful work done.
They're also simple enough to provide good examples when discussing preconditions,
loop invariants, and generic coding.
When writing in, say, C++, you're advised to use the versions
in the `<algorithms>` header rather than these.

The algorithms we'll be studying are:
* [`find(T[] a, T v)`](../algorithms/find.md) - find first occurrence of `v` in `a`
* [`count(T[] a, T v)`](../algorithms/count.md) - count the number of items in `a` equal to `v`
* [`min_element(T[] a)`](../algorithms/min-element.md) - find the minimum element of `a`
* [`max_element(T[] a)`](../algorithms/max-element.md) - find the maximum element of `a`
* [`swap(T[] a, index idx1, index idx2)`](../algorithms/swap.md) - exchange `a[idx1]` and `a[idx2]`
* [`rotate_left(T[] a)`](../algorithms/rotate-left.md) - rotate elements of `a` one element to the left
* [`rotate_right(T[] a)`](../algorithms/rotate-right.md) - rotate elements of `a` one element to the right
* [`copy(T[] a, T[] b)`](../algorithms/copy.md) - copy array `a` to array `b`

In later sections, we'll add these others:
* `is_sorted(T[] a)` - determine if array `a` is sorted (ascending)
* `selection_sort(T[] a)` - sort array `a` into ascending order
* `insertion_sort(T[] a)` - sort array `a` into ascending order
* `lower_bound(T[] a, T v)` - find the first array element in `a` greater than or equal to `v`
* `upper_bound(T[] a, T v)` - find the first array element in `a` strictly greater than `v`

We'll revisit these basic algorithms often, to do such things as:
* Implement versions that operate on a subrange of the array
* Document preconditions
* Document loop invariants
* Work with other conditions (predicates) and comparisons

Finally, these algorithms may be used as homework assignments:
* `any_of(T[] a, T v)` - return `true` if 'a' contains at least one occurrence of `v`, `false` otherwise
* `all_of(T[] a, T v)` - return `true` if every element of `a` is equal to `v`, false otherwise
* `none_of(T[] a, T v)` - return `true` if no element of `a` is equal to `v`, false otherwise
* `fill(T[] a, T v)` - fill `a` with copies of `v`
* `accumulate(T[] a)` - add all the elements of `a` and return the result
* `reverse(T[] a)` - reverse the elements of `a`
* `rotate(T[] a, index lo, index mid, index hi)` - rotate elements of `a[lo,hi)` so that `a[mid]` moves to `a[lo]`

Note that among the first three (`any_of()`, `all_of()`, and `none_of()`),
two are exact opposites of each other;
that is, any inputs for which one returns `true`,
the other will return `false`, and vice versa.
And they are not the two you might first think!
