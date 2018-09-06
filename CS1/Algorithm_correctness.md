TODO: Have this reviewed by someone who knows what they're talking about.

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

We'll start with a discussion of pre-conditions, post-conditions,
and (loop) invariants,
and then revisit our basic array-based algorithms to incorporate
informal proofs of correctness.
Finally, we'll use these concepts to develop a pair of
addition algorithms, which are variations on binary search
called `lower_bound()` and `upper_bound()`.

* [Preconditions and exceptions](CS1/Preconditions.md)
* [Postconditions and testing](CS1/Postconditions.md)
* [Invariants](CS1/Invariants.md)
* [Exception guarantees](CS1/Exception-guarantees.md)
* [Basic algorithms revisited](CS1/Basic-algorithms-proofs.md)
* [Upper/lower bound (binary search)](CS1/Lower-upper-bound.md)
