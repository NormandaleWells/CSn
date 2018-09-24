TODO: Have this reviewed by someone for correctness,
and for consistency with the discrete math course.

Computer programming is a very logical - and therefore very mathematical - activity.
It is often useful to think of a function as a theorem that can be proven,
and various techniques have developed over the years to do so.

The most popular technique is probably the use of Hoare triples,
which are somewhat beyond the scope of this course.
To learn more, you can use [this wikipedia article](https://en.wikipedia.org/wiki/Hoare_logic)
as a starting point.
For the full treatment,
I recommend a good course on discrete mathematics (Csci 2011 or Math 2011 at Normandale).

For our purposes, we'll take a slightly less formal approach.
The "proofs" presented here shuld probably be considered
"correctness arguments" rather than actual proofs.
Anywhere I use the word "proof" in this section,
imagine quotes around word.

Briefly, a precondition is something
that must be true of a function's inputs
in order for the function to do its job.
A postcondition is something which should be
true of the function's outputs.
A function's pre- and postconditions
form a contract; if the caller ensures
that the function's preconditions are met,
the function ensures that the postconditions
are met.  Failure to do so is a coding defect.

(Preconditions and postconditions
can also incorporate and affect global
program state, but as with any use of global
variables, this is best avoided.)

We'll start with a discussion of pre-conditions, post-conditions,
and (loop) invariants,
and then revisit our basic array-based algorithms to incorporate
informal proofs of correctness.
Finally, we'll use these concepts to develop a pair of
additional algorithms, which are variations on binary search
called `lower_bound()` and `upper_bound()`.

Later, we'll extend these concepts to cover instance functions
of a class, at which point the concept of a class invariant
will come into play.

* [Preconditions and exceptions](Preconditions.md)
* [Postconditions and testing](Postconditions.md)
* [Invariants](Invariants.md)
* [Exception guarantees](Exception-guarantees.md)
* [Basic algorithms revisited](Basic-algorithms-proofs.md)
* [Upper/lower bound (binary search)](Lower-upper-bound.md)
