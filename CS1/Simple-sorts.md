# Simple sorting algorithms

Sorting an array means to arrange
the array elements in order
according to some criterion.
For now, we'll keep it simple
and assume that we always want
to sort items in increasing order,
using whatever ordering is defined
for the data we're working with.

Besides providing output that is
generally more convenient for users
to read, sorting data has one other
very important quality: we can search
sorted data using a binary search.
We'll see later that a binary search
is exponentially faster than a
linear search.
In fact, a binary search of a million
items requires no more than 20 comparisons,
rather than the half million required,
on average, for a linear search.

We can use the array algorithms
we've developed to implement
a couple of very simple sorting
algorithms.
Neither of these are suitable
for large arrays,
but they are often useful for small arrays,
and often faster for small arrays
than the more complex algorithms
we'll study later.

The two simple sorting algorithms
we'll look at here are
- [selection sort](Selection-sort.md)
- [insertion sort](Insertion-sort.md)
