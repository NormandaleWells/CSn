# C++ Implementation Notes

Since everything defined here is either
a generic ADT or a set of generic functions,
this entire library is header-based.
There are no .cpp files.

### Arrays

Consistent with our habit of not using
the standard library of the implemenation language,
we avoid using ```vector``` here.
Again, the idea is to show how these ADTs
and algorithms are implemented generally,
not in a specific language.

We do, however, avoid the use of C-style arrays
since we require that arrays know their size.
Instead, we implement a very simple Array<T>
class capable of holding a fixed-size array
of any type.
This is consistent with Java Array class.

Note that our Array<T> is different from the
C++ standard library array<T,N> class.
The latter is a fixed size known at compile time,
whereas ours is a fixed size at run time.

### Tests

Every header adt.h has a corresponding unit test
in test_adt.cpp.
The tests are written using the excellent
[Catch2](https://github.com/catchorg/Catch2) framework.
