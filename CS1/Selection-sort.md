# Selection sort

Selection sort finds the smallest item in the array,
swaps it with the value at index 0,
and then does the same for the
remaining array indices.

For instance, say we start with this array:
```
3 1 5 4 2
```
We start by swapping 1 (the smallest element)
with 3 (the element at index 0)
to get:
```
1 3 5 4 2
```
At ths point, we know that `a[0]` has the
smallest element, so `a[0,1)` is sorted.
Now we need to get the proper element
into `a[1]`,
which we do by swapping it with
the smallest element in the rest
of the array (which is 2):
```
1 2 4 5 3
```
The full sequence of swaps is:
```
3 1 5 4 2
1 3 5 4 2 (swap 1 with 3)
1 2 5 4 3 (swap 2 with 3)
1 2 3 4 5 (swap 3 with 5)
1 2 3 4 5 (swap 4 with itself)
```
Note that the last step finds that
4 is the smallest element in `a[3,5)`,
and so it swaps 4 with itself.
(This could be optimized away,
but the comparison need to determine
this is probably more expensive
than the swap!)

Each step of the algorithm requires is to
1. find the minimum element in the remainder of the array, and
2. swap that element into position.

All we need are the `min_element()`
and `swap()` functions that we've already written.

Since it is often useful to sort
just a subrange of an array,
we provide two versions of `selection_sort`:
one to sort the entire subrange `[0,a.length)`,
and the other to sort any arbitrary subrange.

```
selection_sort(T[] a, index lo, index hi)
	for (index i in [lo,hi-1))
		Inv: is_sorted(a[lo,i))
		Inv: max(a[lo,i)) <= min(a[i,hi))
		index j = min_element(a, i, hi)
		Inv: a[lo,i) <= a[j]
		swap(a, i, j)
		Inv: is_sorted(a[lo,i])
		Inv: a[lo,i) <= a[i] <= a[i+1,hi)
	Post: is_sorted(a[lo,hi))

selection_sort(T[] a)
	selection_sort(a, 0, a.length)
```

In plain English,
we're always moving the smallest remaining
element into `a[i]`,
so we end up with a sorted array.
But let's see if we can prove it.

As usual, the invariants are the key to
proving the correctness of this algorithm.
In this discussion
we'll use `x < a[lo,hi)` as a shorthand for
"`x` is less than everything in `a[lo,hi)`".

There are two loop invariants here:
- The subrange `a[lo,i)` is sorted - that is,
`a[i-1] <= a[i]` for all `i` in `[1,hi)`.
- `max(a[lo,i)) < min(a[i,hi))` - that is,
everything in `a[lo,i)` is less than or equal to
everything in `a[i,hi)`.

These are both trivially true
before the first execution of the loop.

After calling `min_element()`
we know that `a[j] >= a[lo,i)`;
this is a consequence of the second invariant.
Since `swap()` just exchanges `a[i]` and `a[j]`,
after `swap()` we know that `a[i] >= a[lo,i)`,
but, due to the postcondition of `min_element()`
we also know that `a[i] <= a[i+1,hi)`.
Putting these together we have
```
a[lo,i) <= a[i] <= a[i+1,hi)
```
and therefore
```
a[lo,i] is sorted
a[lo,i] < a[i+1,hi)
```
(note the closed ranges).
After incrementing `i` at the end of the loop,
we get
```
a[lo,i) is sorted
a[lo,i) < a[i,hi)
```
which re-establishes our invariants.

After the loop, we can substitute `hi` for `i`
in the first invariant, and get our postcondition.

As a function of the array size,
how many comparisons and moves
are performed by this algorithm?

Our main loop uses the range `a[lo,hi-1)`.
Could we have used `a[lo,hi)` instead?
