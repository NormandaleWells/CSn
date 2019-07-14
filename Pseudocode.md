## Pseudocode

This page discusses the pseudocode
used for code examples throughout the site.

The pseudocode used here is not a formal language,
but should be understandable to anyone
with a semester or two of coursework
that involved writing code.

Unlike most pseudocode languages,
the one used here is strongly typed.
I originally tried using a language
that used implied or inferred types,
with naming conventions to make the
code easier to read, but I found that
to be untenable once I started to
write abstract data types.

There is, however,
one convention that is an important part
of the type system used here:
any type with a single-letter name
is a type parameter of the given
function or class;
that is, it denotes a generic type.

Requirements of type parameters
(what C++ would call type traits or concepts)
are implicit in the code.
For example, if two items of type `T`
are compared in a function,
it can be inferred that items of type `T`
must be comparable.

### Types

Scalar types used here
include `integer`, `float`, `boolean`, and `string`,
with the obvious meaning.

Arrays are indicated with `[]`
after the type name, as in `int[]`
or `T[]`, where the latter is an
array of some generic type.
The size of an array is fixed
at the time it is created,
and there is a `length`
property that returns that size.

There is a special scalar type
called `index` which is used for
indexing arrays.
This type has a special value
`invalid` which indicates an
invalild index.

Abstract data types are defined
using the keyword `class`,
similar to Java or C++.
Classes may have three
types of members:
data, functions, and nested classes.
Data members and nested classes
are assumed to be private;
member functions are assumed to be public.
Access to class members is done via
the `.` operator, e.g. `obj.func()`.

(Yes, I realize it is traditional
to call member functions of a class "methods",
but to paraphrase Shakespeare,
a function by any name
is still a function,
so let's just call them that, shall we?)

The language also makes an explicit
distinction between objects
and pointers to objects,
using & to denote a pointer.
Accessing a class member through a pointer
still uses the `.` operator,
not the C/C++-style `->`.
However, in order not to get bogged down
in issues of memory management,
we assume the presence of a garbage
collector.

### Naming conventions

In general, every "word" of a variable or function name
begins with a lower-case letter,
and the words are separated by underscores:
e.g. `i`, `counter`, `num_items`.

Class names are camel-cased,
starting with upper-case characters.
As noted above, single-character type names
are a special case;
they're used to denote
type parameters.

The language-specific code provided here
uses the conventions generally used
for that language.

### Operators

The usual arithmetic operators are available:
`+`, `-`, `/`, `*`, and `%`
(where the last indicates a modulus operation).
Integer division rounds toward `-infinity`.

Also, the usual comparison and logical
operators are available:
`=`, `!=`, `<`, `>`, `<=`, `>=`, `&&`, `||`, and `!`.

At this writing, I foresee no need
to use operator overloading,
despite its occasional
notational convenience.

### Statement blocks

Blocks of statements are delineated by indentation, rather than curly braces, `begin`/`end` statements, or other syntactic elements.
 
### Conditionals

Conditional statements use
the standard `if/elseif/else` keywords.

### Loops

Both `while` loops and `for` loops are supported.
There are two flavors of `for` loops;
the traditional C-style loop with
the initialization, condition, and increment sections
separated by semi-colons,
and the range-style, for which the `in`
keyword is used to specify a range
over which an index variable ranges.

The most common such loops are
```
for (Node node = List.first; node != null; node = node.next)
```
and
```
for (index i in [0,a.length))
```
Note the use of a half-open range in the latter example.
This is discussed in [half-open ranges](CS1/Half-open-ranges.md).

All ADTs defined here that are collection types
are assumed to be iterable
and may be used in a range-style
for loop.
The means of doing this is,
of course,
left to the specific implemenation language.

### Functions

Functions are defined similarly to C++ and Java.

Functions which return nothing simply leave out the return value in the first line of the function, like this:
```
swap(T[] a, index idx1, index idx2)
```
Arrays are passed to functions by reference.

### Errors

Errors are indicated by an `error` statement
that takes a string describing the message:
```
if hi-lo <= 0
    error "The subrange [lo,hi) cannot be empty."
```
For most languages (e.g. Java, C++, Python),
this would translate into throwing an exception.

None of the code here will have any need to catch exceptions,
so there is no syntax in this pseudocode
for catching exceptions.
We assume here that throwing an exception
terminates the program.

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
the word `Pre:`,
`Post:`, or `Inv:`
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
The condition `a[lo,hi) > 0` for example,
is equivalent to `for all i in [lo,hi), a[i] > 0`.

There is no similar syntax for
an existential qualifier,
but `not a[lo,hi) == val`
serves the same purpose,
since it is equalivalent
to `for some i in [lo,hi), a[i] != val`.

### Comments

Comments begin with `//` and extend to the end of the line.
