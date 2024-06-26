## Binary search algorithms

In this section we'll look at three
binary search functions.
We'll start with the traditional algorithm,
and then show a couple variations
that are often useful.

We'll start with the traditional algorithm
that you'll find in most books.
This takes a sorted array (or subrange of an array)
and a key, and searches the array
for the key value, returning `true`
if the key is found, and `false` otherwise.

>The version you'll find in many books
returns the index rather than a boolean.
The problem with that approach
is that the index is somewhat
meaningless, since in the presence of
repeated key values, you don't know
which is returned.
We'll look at two variations that do
return a meaningful index.

We find the key
by dividing the subarray into
three further subranges
(`lo'` and `hi'` indicate the
original values of `lo` and `hi`,
`a` is the array we're searching):

- `a[lo',lo)` contains elements known to be < `key`
- `a[lo,hi)` contains elements whose relationship to `key` is unknown
- `a[hi,hi')` contains elements known to be > `key`

The first and last ranges are initially empty.
We then enter a loop in which
we calculate the midpoint of the middle range
and compare that value to `key`.
If they're equal, we can return `true`.
If not, we adjust the ranges according
to include the midpoint value
in the proper range.

If we get to a point where `lo == hi`,
we know that the middle range of unchecked
elements is empty without the key value
having been found, and we can then return `false`.

Here is the code for this version of binary search.

```
boolean binary_search(T[] a, index lo, index hi, T key)
	Pre: is_sorted(a[lo,hi))
	while lo < hi
		Inv: a[lo',lo) < key
		Inv: a[hi,hi') > key
		mid = lo + (hi - lo) / 2
		if a[mid] < key
			lo = mid + 1
			// First invariant maintained
		else if a[mid] > key
			hi = mid
			// Second invariant maintained
		else
			Post: a[mid] == key
			return true
	Inv: lo == hi
	Post: a[lo,hi) != key
	// This follows from the two invariants,
	// which produce the separate postconditions
	// a[lo',lo) < key and a[lo,hi') > key
	return false

boolean binary_search(T[]a, T key)
	return binary_search(a, 0, a.length, key)
```
Note that the code is liberally annotated
with assertions showing invariants.
That's because this algorithm is notoriously
difficult to get right,
and the careful use of a loop invariant
goes a long way to ensure that the code is correct.

There are a few subtleties that are worth noting:

First, when `a[mid] < key`, we set `lo = mid + 1`
rather than `lo = mid`.
We do this is for two reasons:

1. It takes advantage of the fact that we know
that `a[mid] < key`, since `a[mid]`
is an element of the subrange `a[lo,mid+1)`.
2. It avoids a potential infinite loop.
Need I tell you how I discovered that?

Second, to be complete, we should also prove that
we never get into a situation where `lo > hi`.
I'll leave that as an exercise to the reader.
Hint: consider how `mid` is computed,
and what happens with ranges of size 1 and 2.

The third subtlety is the way we compute `mid`.
We cannot use the simpler `mid = (lo + hi) / 2`
because a large enough array could cause
`lo + hi` to overflow an integer.

We'll now look at two other binary search
algorithms:

- `lower_bound()` returns the index of the first element
in `a[lo,hi)` that is greater than or equal to the key value.
- `upper_bound()` returns the index of the first element
in `a[lo,hi)` that is strictly greater than the key value.

By "first element"
we mean the one with the lowest index.
The return value may be `hi`
if all the elements are less than the key.

The postcondition for `lower_bound()`
is therefore `a[lo,ret) < key and a[ret,hi) >= key`.
Likewise, the postcondition for `upper_bound()`
is `a[lo,ret) <= key and a[ret,hi) > key`.

If the key value is greater than every value in the array,
both algorithms return `hi`.

There is no need for any special value
to indicate that the value was not found,
since neither algorithm indicates
whether the value was actually found.
Both of these algorithms always return
a valid array index in the range `[lo,hi]`.

One advantage that these algorithms provide
over the basic binary search
is that
```
a[lower_bound(a,key),upper_bound(a,key))
```
is the subrange of `a` containing elements
equal to `key`,
and hence
```
upper_bound(a,key) - lower_bound(a,key)
```
is the number of elements in `a` that are equal to the key.

Another advantage is that `lower_bound()` and `upper_bound()`
indicate where an array element with the key value
could be added to maintain the sortedness of the array.
If it needs to be placed before any other equal elements,
it can be inserted at `a[lower_bound()]`;
if it needs to be placed after any equal elements,
it can be inserted at `a[upper_bound()]`.

The algorithms themselves are very similar
to the `binary_search()` algorithm shown above.
We'll show `lower_bound()`,
and leave `upper_bound()` as an exercise for the reader.

The main difference between lower_bound()
and binary_search() is that we don't return immediately
upon finding an array element equal to the key,
since it may not be the first one.
For lower_bound() the ranges of interest are:

- `a[lo',lo)` contains elements known to be < `key`
- `a[lo,hi)` contains elements whose relationship to `key` is unknown
- `a[hi,hi')` contains elements known to be >= `key`

The only difference from what we defined above
for `binary_search()` is that the upper range
contains elements greater than or equal to the key,
rather than just greater than.

The algorithm is
```
index lower_bound(T[]a, index lo, index hi, T key)
	Pre: is_sorted(a[lo,hi))
	while lo < hi
		Inv: a[lo',lo) < key
		Inv: a[hi,hi') >= key
		mid = lo + (hi - lo) / 2
		if a[mid] < key
			lo = mid + 1
			// First invariant maintained
		else
			hi = mid
			// a[mid] >=key
			// Second invariant maintained
	Inv: lo == hi
	Post: a[lo',lo) < key
	Post: a[lo,hi') >= key
	return lo

index lower_bound(T[] a, T key)
	return lower_bound(a, 0, a.length, key)
```
Since `a[lo',lo) < key`, we know that `a[lo]`
is the first element greater than or equal to
the key, as required.

The algorithm for `upper_bound()`
is nearly identical to `lower_bound()`,
and is left as an exercise.
Hint: the only difference in the code
is that you need to add a single character.
The changes to the assertions
are also minimal.

How many comparisons does `binary_search()`
perform with each loop iteration?
How many comparisons do `lower_bound()`
and `upper_bound()` perform
with each loop iteration?

How many loop iterations
does `binary_search()`?
Does it vary depending on
the data?

How many loop iterations do
`lower_bound()` and `upper_bound()`
perform?
Does it vary depending on the data?

To find the number of elements equal to `key`,
why is it better to compute
`upper_bound(a,key) - lower_bound(a,key)`
than to use `upper_bound()`
to find the first element
and then search linearly
for the first non-matching element?
