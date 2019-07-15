# Insertion sort

Insertion sort moves each element `a[i]`
to its proper position
with the subarray a[0,i),
shifting other elements over
as necessary.
That is, after inserting `a[i]`
into the proper place within `a[0,i)`,
`a[0,i]` (note the closed ranged) is sorted.

For example, say we start with the array
```
5 1 3 2 4
```
Since `a[0,1)` is,
by virtue of have a single element,
sorted, we can start with `a[1]`,
and see that the proper position
for the 1 is before the 5,
so we shift the 5 over
and place the 1 where it belongs:
```
1 5 3 2 4
```
Now we look at `a[2]`,
and see that the 3 belongs between
the 1 and the 5. So we shift the
5 over and place the 3 where it belongs:
```
1 3 5 2 4
```
To get the 2 in position,
we shift the 3 and 5 over:
```
1 2 3 5 4
```
And finally, getting the 4 into
position requires moving the 5:
```
1 2 3 4 5
```
All we need to do is to figure out
1. how to find where an given
element belongs in the already-sorted
part of the array, and
2. how to move that element into position.

The first one is a matter of finding
the index of the first element
strictly greater than the one we're
looking for:
that's our `upper_bound()` algorithm.
The second is our `rotate_right()` algorithm.
So we have the pieces we need
to make implementation fairly straight-forward:
```
insertion_sort(T[] a, index lo, index hi)
	for (index i in [lo+1,hi))
		**Inv:** is_sorted(a[lo,i))
		index j = upper_bound(a,lo,i)
		**Inv:** a[lo,j) <= a[i];
		**Inv:** a[i] < a[j+1,i)
		rotate_right(a, j, i+1)
		**Inv:** is_sorted(a[lo,i])
```



Could we use `lower_bound()`
instead of `upper_bound()`?
If so, would there be any difference
in behavior?

As a function of the array size,
how many comparisons and moves
are performed by this algorithm?

What would happen if we looped
over the range a[lo,hi)
instead of a[lo+1,hi)?

How does this code handle the case where
`a[i]` > a[lo,i)`?  (I.e, when `a[i]`
is already in position and no movement
is necessary.)