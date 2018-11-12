# General Implementation Notes

The pseudocode used here
should be able to be implemented
with any language supporting
the follow features:

* Fixed-length arrays (that "know" their length)
* At least minimal support for generics
* Support for encapsulation

For the most part,
the implemenations do not take advantage
of the language library.
The reason for this is that the
purpose of CS1/CS2 courses is to
teach general programming,
not a specific language.
So, for example,
we don't use the Java ```ArrayList```
class or the C++ ```vector``` class,
prefering instead to use fixed-size
arrays, and demonstrate
how a resizeable array is implemented.

The general principle is that it should be possible
to take whatever is implemented here
and use it as the basis of an implementation
in any other language.

Where possible,
I tend to define APIs using interfaces.
While not strictly necessary,
it has two advantages:
* It ensures that I use the same API consistently
across implementations of it.
* It makes testing easier,
as a single test written to the API
can be used for all implementations.

Here are the pages
containing the implementation notes
for each language:

[Java](java-notes.md)

[C++](cpp-notes.md)

[Python](python-notes.md)
