## Invariants

A loop invariant is a logical condition
that should always be true at some particular
point in a function.
We're generally most interested in conditions
that are true at the start of every execution
of a loop.
We'll see that loop invariants are generally
very similar to a functions postconditions;
in fact, the postconditions will, in most cases,
follow directly from the loop invariant.

Note that for a for loop,
the "top" of the loop is when the loop condition
is tested.

When using loop invariants,
it is necessary to demonstrate two things:
* You must show that the loop invariant is
true before the first execution of the loop.
* You must show that the loop invariant is
re-established by the action of the loop.

Note that within the loop itself,
it is possible that the loop invariant
is temporarily invalidated.
The important thing is for the invariant
to be true at the top of the loop.