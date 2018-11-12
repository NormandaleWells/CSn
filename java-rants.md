# Java rants

## Why not use interfaces for operator overloading?

There are times when I think
that Java goes a little too far
in trying not to be C++,
most having to do with
operator overloading.

### comparing objects

Say type ```T1 implements Comparable<T2>```.
Then why can't the compiler allows us to
use the standard comparison operators,
and convert them to calls to ```.compareTo()```?

For example, if ```T1``` and ```T2``` are as above,
and ```obj1``` and ```obj2```
are of types ```T1``` and ```T2``` respectively,
```
if (obj1 < obj2)
	// do something
```
could be automatically converted to
```
if (obj1.compareTo(obj2))
	// do something
```
The same could, I think, be done
any time a object
that ```implements Comparator<T1,T2>```
is in scope.

### subscripting

Why can't Java use an interface like this:
```
public interface Indexable<Key,Value> {
	Value get(Key key);
	void put(Key key, Value, value);
}
```
to support more general use
of array indexing?

This would allow, for example
```
public class SymbolTable<K,V> implements Indexable<K,V> {
	...
}

{
	SymbolTable <String,Integer> st = new SymbolTable<>();
	String s = "xyzzy";
	st[s] = 42;	        // calls put()
	Integer i = st[s]   // calls get()
}
```

## Primitive types cannot be type parameters.

This really isn't a rant,
since I understand the reason for it,
but I do wish we could use primitive types
as generic type parameters.

## Objects always use the heap.

Java only allows user-defined objects to be
allocated on the heap.
This means that using a user-defined
object always involves an extra level
of indirection.
On today's processors,
this can drastically reduce efficiency;
besides the extra level of indirection,
it also works against the use of caches.
It's also uses (often considerably)
more memory due to the 16-byte overhead
for each object, and reference itself.

For example,
a simple array of 2D points,
using ```double``` as the underlying type,
could use 16+16N bytes of memory
(16 for the array object overhead,
and 16 per Point2D object),
and each access is a single read.
Sequential reads can take advantage
of the processor caches.

But because Point2D is -- necessarily -- a
reference type, it really uses 16+40N
bytes of memory (16 for the array object,
8N for the references in the array,
16 for each Point2D object,
and 16 bytes of object overhead
for each Point2D object).
Each access requires 2 memory reads,
and sequential access is far less
likely to be cache-friendly.

Put another way,
even if the compiler, JVM, and JIT compiler
can produce code as efficient as possible,
they can do nothing to make up for the
inherent inefficiencies in the
organization of the data.

## Object-orientation is the only option.

Java was invented at a time
when object-oriented programming
was supposed to solve the
all the problems that make
writing software difficult
and error-prone.
Unfortunately,
Java enforces the view that
everything must be part of
a class, even functions that
in a language like C would
be a stand-alone function.

Some people go so far as to suggest
that "utility classes",
which consist of nothing but
static functions,
are an anti-pattern
that should not be used.
For an exmplae of why I consider
this ridiculous, consider this
article:
[OOP Alternative to Utility Classes](https://www.yegor256.com/2014/05/05/oop-alternative-to-utility-classes.html).
The author seriously suggests -- apparently
with a straight face -- that the
proper way to implement a simple ```max(a,b)```
function is to wrap it in a class:
```
public class Max implements Number {
  private final int a;
  private final int b;
  public Max(int x, int y) {
    this.a = x;
    this.b = y;
  }
  @Override
  public int intValue() {
    return this.a > this.b ? this.a : this.b;
  }
}
```
Sorry, but when I call ```max(a,b)```
I do NOT expect to pay the overhead
for an object allocation
(and subsequent garbage collection).

(The real irony, IMO, is that this
code does not even compile,
since it lacks implementations of
several more functions that are
part of the Number interface.)

## Garbage collection solves the wrong problem.

At first glance,
garbage collection seems like
a really good idea,
but it really solves the wrong problem.
The real problem is not memory leakage,
but resource leakage,
where resources are things like
file handles, network connections,
database connections, etc.

Consider what happens when you
open a file,
but forget to close it.
The file will be closed
at some point,
but it's nondeterministic,
so trying to open the file again
may or not work
depending on whether the file object
was garbage collected.

Of course, Java does have the
try-with-resources method,
but I fail to see how remembering
to use that is all that different
from remembering to use close -- or,
for that matter, remember to free memory.

This is one thing that I think C++
gets exactly right.
Rather than leaving it to the user
to use a type correctly,
the type itself can take advantage
of deterministic destruction of objects
to automatically clean up after itself.

## Sorry, Java, you are not pass-by-value.

Java folks will tell you that Java always
passes objects by value,
with the dodge that when you pass
an object to a function,
you're passing the value of the reference.

By the defintion I'm familiar with,
if Java passed objects by value,
then this code:
```
public class HideInt {
	private int i;
	public HideInt(int ai) { i = ai; }
	public void setI(int ai) { i = ai; }
	public void getI() { return i; }
}

public class NotByValue {
	private static void f1(HideInt hi) {
		hi.setI(1);
	}

	public static void main(String[] args) {
		HideInt hi(0);
		f(hi);
		System.out.printf("%d\n", hi.getI());
	}
}
```
would print ```0``` rather than ```1```.