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
original values of `lo` and `hi`):

- `A[lo',lo)` contains elements known to be < `key`
- `A[lo,hi)` contains elements whose relationship to `key` is unknown
- `A[hi,hi')` contains elements known to be > `key`

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
boolean binary_search(A, lo, hi, key)
	assert is_sorted(A[lo,hi))
	while i < j
		assert A[lo',lo) < key
		assert A[hi,hi') > key
		mid = lo + (hi - lo) / 2
		if A[mid] < key
			lo = mid + 1
		else if A[mid] > key
			hi = mid
		else
			return true
	assert lo == hi
	assert a[lo',lo) < key
	assert a[lo,hi') > key
	return false

boolean binary_search(A, key)
	return binary_search(A, 0, a.length, key)
```
Note that the code is liberally annotated
with assertions showing invariants.
That's because this algorithm is notoriously
difficult to get right,
and the careful use of a loop invariant
goes a long way to ensure that the code is correct.

There are a few subtleties that are worth noting:

First, when `A[mid] < key`, we set `lo = mid + 1`
rather than `lo = mid`.
We do this is for two reasons:

1. It takes advantage of the fact that we know
that `A[mid] > key`, since `A[mid]`
is an element of the subrange `A[lo,mid+1)`.
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
in `A[lo,hi)` that is greater than or equal to the key value.
- `upper_bound()` returns the index of the first element
in `A[lo,hi)` that is strictly greater than the key value.

If the key value is greater than every value in the array,
both algorithms return `hi`.
By "first element"
we mean the one with the lowest index.

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
A[lower_bound(A,key),upper_bound(A,key))
```
is the subrange of `A` containing elements
equal to `key`,
and hence
```
upper_bound(A,key) - lower_bound(A,key)
```
is the number of elements in `A` that are equal to the key.

Another advantage is that `lower_bound()` and `upper_bound()`
indicate where an array element with the key value
could be added to maintain the sortedness of the array.
If it needs to be placed before any other equal elements,
it can be inserted at `A[lower_bound()]`;
if it needs to be placed after any equal elements,
it can be inserted at `A[upper_bound()]`.

The algorithms themselves are very similar
to the `binary_search()` algorithm shown above.
We'll show `lower_bound()`,
and leave `upper_bound()` as an exercise for the reader.

The main difference between lower_bound()
and binary_search() is that we don't return immediately
upon finding an array element equal to the key,
since it may not be the first one.
For upper_bound() the ranges of interest are:

- `A[lo',lo)` contains elements known to be < `key`
- `A[lo,hi)` contains elements whose relationship to `key` is unknown
- `A[hi,hi')` contains elements known to be >= `key`

The only difference from what we defined above
for `binary_search()` is that the upper range
contains elements greater than or equal to the key,
rather than just greater than.

The algorithm is
```
index lower_bound(A, lo, hi, key)
	assert is_sorted(A[lo,hi))
	while lo < hi
		assert A[lo',lo) < key
		assert A[hi,hi') >= key
		mid = lo + (hi - lo) / 2
		if A[mid] < key
			lo = mid + 1
		else
			hi = mid
	assert lo == hi
	assert a[lo',lo) < key
	assert a[hi,hi') >= key
	return lo

index lower_bound(A, key)
	return lower_bound(A, 0, a.length, key)
```
Since `A[lo',lo) < key`, we know that `A[lo]`
is the first element greater than or equal to
the key, as required.

The algorithm for `upper_bound()`
is nearly identical to `lower_bound()`,
and is left as an exercise.
Hint: the only difference in the code
is that you need to add a single character.
The changes to the assertions
are also minimal.
