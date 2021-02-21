"""
arrayutils - simple array algorithms

find - find the first matching value within an array
find_if - find the first array element for which a given predicate is True
count - count the number of elements matching a value
count_if - count the number of array elements for which a given predicate is True

Python does not allow function overloading, so the subrange versions of
these algorithms use keyword arguments for lo and hi.  The use of hi=-1
is a kludge due to the fact that Python won't allow hi=len(a).
"""

def find(a, value, lo=0, hi=-1):
    """
    Return the index of the first element of list 'a' which is equal
    to 'value'.

    Keyword argumens:
    lo -- the lo end of the subrange to search
    hi -- the hi end of the subrange to search (exclusive)
    """

    if hi == -1: hi = len(a)

    assert type(a) == type([])
    assert 0 <= lo <= hi <= len(a)
    assert len(a) == 0 or type(value) == type(a[0])

    return find_if(a, lambda a : a == value, lo=lo, hi=hi)

def find_if(a, pred, lo=0, hi=-1):
    """
    Return the index of the first element of list 'a' for which 'pred'
    returns True.

    Keyword argumens:
    lo -- the lo end of the subrange to search
    hi -- the hi end of the subrange to search (exclusive)
    """

    if hi == -1: hi = len(a)

    assert type(a) == type([])
    assert type(lo) == type(hi) == type(0)
    assert 0 <= lo <= hi <= len(a)

    for i in range(lo, hi):
        if pred(a[i]):
            return i
    return -1

def count(a, value, lo=0, hi=-1):
    """
    Return the number of elements of list 'a' which are equal
    to 'value'.

    Keyword argumens:
    lo -- the lo end of the subrange to search
    hi -- the hi end of the subrange to search (exclusive)
    """

    if hi == -1: hi = len(a)

    assert type(a) == type([])
    assert 0 <= lo <= hi <= len(a)
    assert len(a) == 0 or type(value) == type(a[0])

    return count_if(a, lambda a : a == value, lo=lo, hi=hi)

def count_if(a, pred, lo=0, hi=-1):
    """
    Return the number of elements of list 'a' for which 'pred'
    returns True.

    Keyword argumens:
    lo -- the lo end of the subrange to search
    hi -- the hi end of the subrange to search (exclusive)
    """

    if hi == -1: hi = len(a)

    assert type(a) == type([])
    assert type(lo) == type(hi) == type(0)
    assert 0 <= lo <= hi <= len(a)

    c = 0
    for i in range(lo, hi):
        if pred(a[i]):
            c += 1
    return c
