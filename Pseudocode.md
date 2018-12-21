## Pseudocode

This page discusses the pseudocode used for code examples throughout the site.

The pseudocode used here is not a formal language, but should be understandable to anyone with a semester or two of coursework
that involved writing code.  The documentation here is really a set of conventions.

### Types

Data types within collections will generally not be specified.  Any necessary functionality will be documented
(e.g. "the elements of `A` must be comparable").  Collection types have a `.type` property that can be used to get
the type of data stored in the collection.  All elements of a collection will have the same type.

Scalar types will, when appropriate, be typed as `integer`, `float`, `boolean`, or `string`, with the obvious meaning.
There is also a scalar type `index` which can be used to indicate an array index.  The `index` type has a special value
`invalid` which indicates an invalid index.  I would expect that most language implementations would use -1 as that
special value.

### Variable names

Variables whose names begin with an uppercase character are arrays or other collections, and those with names beginning
with lowercase characters are scalars or aggregates.  Collections and aggregates may have properties, specified using dot
notation, e.g. A.length or node.next.  The available properties for a type will be enumerated in the accompanying discussion
of that type, along with their meaning.  Property names will begin with a lowercase letter.

Arrays are indexed using `[]`.

Variable names for aggregate types (e.g. the Node type used by linked lists) will begin with a lowercase character,
mainly because these are often also used as properties of a collection type (e.g. List.first).

Function names begin with a lowercase character.

Multiple words within a variable or function name will be separated by underscores (e.g. min_index).  The language-specific
implementations will, of course, follow the standard conventions for that language (e.g. camelCase for names in Java code).

### Operators

The usual arithmetic operators are available: `+`, `-`, `/`, `*`, and `%` (where the last indicates a modulus operation).
Integer division rounds toward `-infinity`.

### Statement blocks

Blocks of statements are delineated by indentation, rather than curly braces, `begin`/`end` statements, or other syntactic elements.
 
### Conditionals

Conditional statements use the standard `if/elseif/else` keywords.

### Loops

Both `while` loops and `for` loops are supported.  There are two flavors of `for` loops; the traditional C-style loop with
the initialization, condition, and increment sections separated by semi-colons, and the range-style, for which the `in`
keyword is used to specify a range over which an index variable ranges.  The most common such loops are
```
for (node = List.first; node != null; node = node.next)
```
and
```
for (i in [0,a.length))
```
Note the use of a half-open range in the latter example.  This is discussed in [half-open ranges](CS1/Half-open-ranges.md).

### Functions

Functions are defined similarly to C++ and Java, except that argument types are often left out if they're obvious from the context.
For example, in
```
index find(A, v)
```
it should obvious from the context that `A` is an array, and `v` is an object of the same type stored in the array.

Functions which return nothing simply leave out the return value in the first line of the function, like this:
```
swap(A, idx1, idx2)
```

### Errors

Errors are indicated by an `error` statement
that takes a string describing the message:
```
if hi-lo <= 0
    error "The subrange [lo,hi) cannot be empty."
```
For most languages (e.g. Java, C++, Python),
this will translate into throwing an exception.

None of the code here will have any need to catch exceptions,
so there is no syntax in this pseudocode
for catching exceptions.

### Assertions (preconditions, postconditions, and invariants)

There are special statements used here
for documenting preconditions,
postconditions, and loop invariants.
There are referred to generically here
as assertions.
These statements are modelled after
Java's ```assert``` statement.

As assertion is not an executable statement;
rather, it is a way of documenting
that a given logical condition must always
be true at that point in the function.

Syntactically, an assertion is simply
the word ```Pre:```,
```Post:```, or ```Inv:```
followed by the logical condition which should be true.
For example
```
Pre: x >= 0
```
The capitalization and use of a colon
are intended to make these statements
stand out.

Assertions have a special syntax
for asserting a condition that must be true
for every element in a consecutive subrange
of an array.
The condition ```a[lo,hi) > 0```, for example,
is equivalent to ```for all i in [lo,hi), a[i] > 0```.

There is no similar syntax for
an existential qualifier,
but ```not a[lo,hi) == val```
serves the same purpose,
since it is equalivalent
to ```for some i in [lo,hi), a[i] != val```.

### Comments

Comments begin with `//` and extend to the end of the line.
