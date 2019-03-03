## Postconditions

A postcondition of an algorithm
is a logical statement that must be true
after the algorithm executes.
That is, it defines what the algorithm does.

Take, for example, our sqrt function
```
real sqrt(real x)
  real t = x
  while abs(t - x/t) > 1e-15 * t
    t = (c/t + t) / 2
  return t
```
which has the postcondition that the return value,
multiplied by itself,
will evaluate to the parameter;
that is, `ret * ret = x`.
(Note that we use `ret` to denote the return value.)

If a function's preconditions are satisfied,
not satisfying a postcondition
is a defect in the algorithm
or its implementation.
Postconditions are generally tested
via unit tests,
which it is the responsibility
of the algorithm implementor to provide.

A suitably paranoid caller could, of course,
also verify postconditions,
but that should never be necessary.
