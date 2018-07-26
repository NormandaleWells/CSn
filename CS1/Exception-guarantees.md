## Exception guarantees

There are three main sources of errors:

1. Failure to meet a precondition.

This is a failure on the part of the caller of a function,
and should be signalled by throwing an exception.

2. Environmental conditions

This would include, for example, a non-existent file,
invalid user-entered data, a disk crash, or any other
situation that results in a function not being to
establish it's postcondition.  It should be signalled
by throwing an exception.

3. A program defect

Any other failure to meet a postcondition is a defect
in the function that needs to be fixed.

Even when an algorithm fails (throws an exception),
it must provide one of these three guarantees:
* The no-fail guarantee
* The strong guarantee
* The basic guarantee

### The no-fail guarantee
The no-fail guarantee means that the algorithm
cannot fail.
If it calls any other functions that may fail,
those failures are caught and handled within
the algorithm.

TODO: Research whether a function that throws exceptions
ONLY to signal unmet preconditions is no-fail.  It would
not be nothrow in C++, but perhaps we can draw a distinction
between that and a more generic meaning of no-fail.

TODO: examples

### The strong guarantee
The strong guarantee means that if the algorithm fails,
the system state is unchanged.

TODO: examples

### The basic guarantee
The minimal guarantee that an algorithm can provide
is the basic guarantee.
This means that if the algorithm failed,
the system is left in a consistent state.

TODO: examples
