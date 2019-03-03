# Exception guarantees

For the purpose of this article,
the word failure has a more specific
definition than usual:
A function is considered to have failed
when it throws an exception,
which it should do any time it cannot
satisfy its postcondition.

Also, we make a distinction here
between two types of exceptions:

1. Runtime exceptions

A runtime exception indicates that the
cause of the exception is external
to the function iself.

2. Logic exceptions

A logic exception indicates that the
cause of the exception is internal
to the function or the class its in;
that is, it's a coding error.

## Sources of errors

There are two main sources of errors
that may prevent a function from
satisfying its postcondition:

1. An unsatisfied precondition.

If the unmet precondition is due to an invalid
function argument,
this is an error on the part of the caller,
and should be signalled by throwing a runtime
exception.

If the unmet precondition is an invalid
class invariant,
it is a coding defect elsewhere in the class,
and should be signalled by throwing a logic error.
(Of course, such an exception should be logged
to make people aware of the problem
so it can be fixed.)

2. Environmental conditions

This would include, for example, a non-existent file,
invalid user-entered data, a disk crash, or any other
situation that results in a function not being to
establish its postcondition.  It should be signalled
by throwing a runtime exception.

Any other failure to meet a postcondition is a defect
in the function.
In this case, you don't throw an exception:
you fix the problem!

## The guarantees

Even when an algorithm fails
(meaning it throws an exception),
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

Note that C++ requires that destructors and swap functions
be no-fail; the correctness guarantees of other functions
depends on those never throwing exceptions.

TODO: Research whether a function that throws exceptions
ONLY to signal unmet preconditions is no-fail.  It would
not be `nothrow` in C++, but perhaps we can draw a distinction
between that and a more generic meaning of no-fail.

TODO: examples

### The strong guarantee
The strong guarantee means that if the algorithm fails,
the system state is unchanged.
This generally requires one of two things:

1. A means of making no state changes
until anything that may throw an exception
has been completed.

2. Some means of rolling back the system state
to what it was before the function was called.

TODO: examples (preferably one of each)

### The basic guarantee
The minimal guarantee that an algorithm can provide
is the basic guarantee.
This means that if the algorithm failed,
the system is left in a consistent state.

TODO: examples

### That's all, folks.

There is no weaker guartanee.
Code that cannot at least meet
the basic guarantee is defective.
