This work is guided by a few basic principles:

## Working code
I want to eventually supply working code for everything discussed here,
in at least these languages:
* **Java** is a nice language for the CSn sequence,
since we don't have to worry (much) about memory management.
It also has a simple syntax, and is easy to learn.
* **C++** is my main language, but until C++11, I would never have considered using it
for this course.
Modern C++ has features that make memory management nearly trivial,
and it has no close peer in regard to generic programming and efficiency.
* **Python** has become a very popular language,
largely due to its ability to interface with libraries
like NumPy, SciPy, and SymPy.

There are also a couple of new languages which I find intriguing,
and which may get some support here:
* **Kotlin** "fixes" much of what I don't like about Java.
* **Rust** retains the uberefficiency of C++, but has interesting facilities
to control object access and lifetime.

## Standard libraries
One of the ironies of teaching a data structures and algorithms course
is that every language's standard library already contains
the data structures and algorithms studied in that course.
I've chosen here to ignore that fact.
The idea here is that the purpose of the course is to show the student
how these algorithms work,
and the best way to do that is to provide an implementation she can
study and modify.

But for heaven's sake,
in real-world code use the library that comes with your language.
It is probably more efficient and certainly better tested
than anything you'll find in the code repository here.

## Array indexing
Every modern language starts arrays at 0,
and most books on algorithms completely ignore this simple fact.
(Sedgewick and Wayne's _Algorithms, 4th edition_ is one notable exception.
See [references](References.md).)
I often wonder how many off-by-one errors are caused
by programmers improperly translating a book's 1-based array pseudocode
into their 0-based language of choice.

Furthermore, 0-based array indexing invites the use of [half-open ranges](CS1/Half-open-ranges),
which are used extensively throughout the code here.
For good examples of how the use of half-open simplify programming
and make it easier to analyze code for correctness,
see the code for `upper_bound`, `lower_bound`, and `quicksort_3way`.

## Coding Style
I take a very pragmatic approach to programming.
If I think having multiple `return` statements in a function will make it
more readable, I do it.
If I think a `break` or `continue` statement makes the code more readable,
I do it.
Send complaints to /dev/null.

I also do not believe any one paradigm
(as in object-oriented, generic, or functional programming)
is the be-all and end-all of how to write software.
Each paradigm has its uses,
and each contributes good ideas to the writing of good code.
Different languages support these paradigms to different degrees,
so the code here for different languages
may be inconsistent in that regard.
My C++ code will tend to be more generic,
my Java code more object-oriented,
and both incorporate functional programming principles
where appropriate.

## Writing style
You can have my Oxford comma when you can pry it out of my cold, dying hands.
After all, without the Oxford comma,
I could claim that some of my major influences
would be my parents, Robert Sedgewick and David Gries.
