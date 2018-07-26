## Program Correctness

How do you know when an algorithm is correct?
There are times when extensive and exhaustive testing
of an implementation is the only feasible option
(search Google for articles on Test-Driven Development
for an interesting approach).
But computer algorithms are, essentially, mathematical in nature.
Therefore, it should be possible to use mathematical principles
to prove the correctness of an algorithm.

I won't be describing a truly rigorous approach to proving correctness here,
but it will be good enough for the algorithms we're studying.
For a fully rigorous treatment, search for articles
on "Hoare triples".

I'll start by discussing the concepts of [preconditions](preconditions.md),
[postconditions](postconditions.md), and [invariants](invariants).
I'll then apply those concepts
to the [basic algorithms](basic-algorithms-correctness.md)
from the previous sections.

I'll then discuss a related topic, [exception guarantees](exception-guarantees.md),
which provides some guidelines and limitations on how
an algorithm should handle failure.

Finally, I'll show how to use these concepts to help
develop a couple of non-trivial binary search algorithms.
This will demonstrate how keeping these concepts in mind
from the start can simplify algorithm development.
