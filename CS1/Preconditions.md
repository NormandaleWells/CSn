## Preconditions

A precondition of an algorithm is a logical statement
that must be true for the algorithm to
function correctly (that is, return a correct result).
It generally involves only the algorithm parameters,
but later we'll study algorithms that execute within
the context of an "object" which contains some state,
defined by a "class"; in this case,
the object state may be part of the precondition.

For example, consider a algorithm to calculate a square root.
If the output parameter is a real number,
we need to avoid a complex result,
and therefore the input parameter must be non-negative.
That is, the algorithm
```
real sqrt(real x)
  real t = x
  while abs(t - x/t) > 1e-15 * t
    t = (c/t + t) / 2
  return t
```
has the precondition that `x >= 0`.
(NOTE: This code is adapted
from Sedgewick's and Wayne's *Algorithms, fourth edition*.
See [references](References.md).)

It is the responsibility of the caller
to establish the validity of an algorithm's precondition(s).
An algorithm may check that its preconditions are met,
but it may also choose to simply
fail - often spectacularly and without warning -
if a precondition is violated.
For example, the `sqrt` algorithm shown above
will loop forever if the precondition is not met.

In general,
if checking a precondition can be done in constant time,
it's probably worthwhile to do so.
Another useful guideline is that if an algorithm
can check a prediction without changing its time
complexity, it's probably worthwhile to do so.

For the pseudocode used here,
failure to meet a precondition
is flagged via an `assert` statement.
This causes the program to simply stop executing.
That is, the `sqrt` function shown above would be:
```
real sqrt(real x)
  assert x >= 0
  real t = x
  while abs(t - x/t) > 1e-15 * t
    t = (c/t + t) / 2
  return t
```
In real world code there are generally better options,
as discussed below.

In many languages,
it is possible to differentiate between "debug"
and "release" builds of a program, and do more stringent
checking of preconditions in a debug build.

If an algorithm determines that a precondition is not met,
it has several options.
(Spoiler alert: I saved the best for last.)

### Option 1: ignore it
The algorithm may simply ignore failed precondition
and carry on as best it can.
In many cases, the unmet precondition will cause a failure
in the algorithm,
such as a divide by zero or an out-of-range array subscript.

One problem with this approach is that the caller may have
no idea why an invalid result was generated,
or even be aware that the result is invalid.
For example, we'll see later that a binary search algorithm
has a precondition that the data it is searching must be sorted.
However, it will generally run to completion on unsorted data,
giving a completely meaningless result.

Another problem with this approach is that it may cause
the algorithm to never terminate.
This can be seen
with the `sqrt` algorithm shown above.
If `x < 0`, it will simply loop forever.  (Why?)
This is one case where checking a precondition
is highly recommended.

### Option 2: terminate the program
Draconian though it may be, this is sometimes the best option.
For example, if a `launch_rocket` algorithm has a precondition
that the rocket must have fuel, and this precondition is violated,
there's really not much else it could do.
Still, this option should be reserved for extreme situations.

### Option 3: return an error code
This is the only option available to many older languages,
like C and Fortran.
Unfortunately, if the algorithm is something like a mathematical
function that returns a calculated value,
using an error code requires awkward changes to the parameters.

For example, the `sqrt` algorithm would have to be changed to either
```
real sqrt(real x, integer ref ec)
```
or
```
integer sqrt(real x, real ref result)
```
Both these techniques have the problem of require some sort
of pass-by-reference facility in the language,
and the second makes it impossible to use the `sqrt`
function as part of a larger calculation.

Perhaps the biggest problem with using error codes
is that the caller has to remember to check them,
and far too often does not bother to.
Have you ever seen C code that checks the value returned
by `printf()`?
(Did you even know that `printf()` returns a value?)

One variant on this technique is to use a global variable
that indicates an error.
The C library uses this technique with a global variable called `errno`.
But with this method,
not only does the caller need to reemember to check the error code,
but it needs to be checked before the next call to a function
that can change the global error code.

Also, in a multithreaded environment,
each thread needs its own copy of the error code.
This is difficult to do in a portable way
unless the language itself supports it
(as C++ does with `thread_local` storage).

### Option 4: throw an exception
The last option, in languages that support it,
is to throw an exception.
An exception causes control to transfer to a corresponding
"catch" block.
This forces the caller to either anticipate the
possible failure,
or take the conscious step of allowing the program
to terminate if the exception is never caught.

The fact that the caller is forced to anticipate failure
is often seen as a problem with this option.
IMO, that's really an advantage.
Anything that forces a programmer to think about
what he or she is doing is a Good Thing.

Also, exceptions prevent invalid partial results
from percolating through the system
as they would when error codes are ignored.

Exceptions work well with error logging systems.
A `catch` block at the top level can catch and log
any uncaught exceptions,
so at least there's a record of what failed.
This cannot be done (easily) with error codes.

Finally, using exceptions allows the programmer to write
code such that all the error handling is in one place,
rather than strewn throughout every function.
This makes for much cleaner-looking code.

One problem with exceptions is that they generally
cannot be used in real-time code.
This is because it is impossible to determine
how long it takes the run-time system to throw an exception.
Ironically, this means the Bjarne Stroustrup,
the inventor of the C++ language,
cannot use exceptions in some of his work
(fighter jet avionics is about as real-time
as you can get).

For more on error handling and exceptions,
see [exception guarantees](Exception-guarantees.md).

