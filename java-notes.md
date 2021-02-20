# Java implementation notes

Because Java requires all code to be in a class,
the basic array algorithms (```find```, ```count```, etc.)
are implemented in a class called ```ArrayUtils```.
Yes, I'm using a utility class in Java;
sometimes a function is just a function.
Send complaints to /dev/null.

Each of the basic array algorithms
is really a family of algorithms;
each family has a single "real"
implemenation that is called
by the others.

For example, ```find``` is really implemented
using a half-open subrange and a Predicate,
as that's the most general version.
For consistency, we supply an ```IsEqual```
predicate that simply forwards the
```test()``` function to ```.equals()```.

TODO: Test with java.util.function.predicate.

The algorithms that require comparisons of objects
(```max_element()```, ```lower_bound()```, etc.)
are ultimately implemented in terms of
a Comparator object;
for convenience and consistency,
we provide a ```WrapComparable``` class
that implements Comparator
by forwarding calls to ```.compare()```
to ```Comparable.compareTo()```.
