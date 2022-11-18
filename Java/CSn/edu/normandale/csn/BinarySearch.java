package edu.normandale.csn;

import java.util.Comparator;

public final class BinarySearch {

	// Don't allow an object of this type to be created.
	private BinarySearch() {
	}
	
	// TODO: incorporate comments and general versions of rotate
	// from fall 2017 ClassDemos.

	// NOTE: In preconditions, I use a shortcut syntax to refer to
	// everything in a half-open range of an array.  The notation
	// a[lo,hi) refers to every element of array a in the range [lo,hi).
	// For example
	//
	//		a[lo,hi) < 0
	//
	// is a shortcut for "every element a[i] with lo <= i < hi
	// is less than 0".

	// checkArguments() is used to check for preconditions; it checks to
	// make sure the array is not null.
	private static <T> boolean checkArguments(T[] a, boolean allowEmpty) {
		assert a != null;
		assert allowEmpty || a.length > 0;
		return true;
	}

	// checkArguments() is used to check for preconditions; it checks to
	// make sure the array is not null, and that 'lo' and 'hi' are within
	// the bounds of the array.
	private static <T> boolean checkArguments(T[] a, int lo, int hi, boolean allowEmpty) {
		assert a != null;
		assert (lo >= 0) && (lo <= a.length);
		assert (hi >= 0) && (hi <= a.length);
		assert allowEmpty || hi - lo > 0;
		return true;
	}

	// lowerBound returns the index of the first (lowest-index)
	// element of sub-array a[lo,hi) that is greater than or equal
	// to the given value.
	// Preconditions:
	// 		a != null
	// 		0 <= lo <= a.length
	// 		0 <= hi <= a.length
	// 		hi - lo > 0
	// Postconditions (let r be the return value):
	// 		for i in [lo,r) a[i] < value
	// 		for i in [r,hi) a[i] >= value
	public static <T> int lowerBound(T[] a, int lo, int hi, T value, Comparator<T> c) {
		checkArguments(a, lo, hi, true);
		assert ArrayUtils.isSorted(a, lo, hi, c);
		// In all invariants, lo' and hi' are the original values
		// of lo and hi.
		// Note that due to the invariant that mid is in [lo,hi),
		// we will never get lo > hi.
		while (lo < hi) {
			// for all j in [lo',lo) a[j] < value
			// for all j in [hi,hi') a[j] >= value
			int mid = lo + (hi - lo) / 2;
			// mid in [lo,hi)
			if (c.compare(a[mid], value) < 0)
				lo = mid + 1;
			// for all j in [lo',lo) a[j] < value
			// NOTE: mid + 1 is important to guarantee termination!
			else
				hi = mid;
			// for all j in [hi,hi') a[j] >= value
		}
		// for all j in [lo',hi) a[j] < value
		// for all j in [hi,hi') a[j] >= value
		return hi;
	}

	// lowerBound returns the index of the first (lowest-index)
	// element of array 'a' that is greater than or equal to the
	// given value.
	// Preconditions:
	// a != null
	// a.length > 0
	// Postconditions (let r be the return value):
	// for i in [0,r) a[i] < value
	// for i in [r,a.length) a[i] >= value
	public static <T> int lowerBound(T[] a, T value, Comparator<T> c) {
		checkArguments(a, true);
		assert ArrayUtils.isSorted(a, c);
		return lowerBound(a, 0, a.length, value, c);
	}

	// lowerBound returns the index of the first (lowest-index)
	// element of sub-array a[lo,hi) that is greater than or equal
	// to the given value.
	// Preconditions:
	// 		a != null
	// 		0 <= lo <= a.length
	// 		0 <= hi <= a.length
	// 		hi - lo > 0
	// Postconditions (let r be the return value):
	// 		for i in [lo,r) a[i] < value
	// 		for i in [r,hi) a[i] >= value
	public static <T extends Comparable<T>> int lowerBound(T[] a, int lo, int hi, T value) {
		checkArguments(a, lo, hi, true);
		assert ArrayUtils.isSorted(a, lo, hi);
		return lowerBound(a, lo, hi, value, new WrapComparable<T>());
	}

	// lowerBound returns the index of the first (lowest-index)
	// element of array 'a' that is greater than or equal to the
	// given value.
	// Preconditions:
	// a != null
	// a.length > 0
	// Postconditions (let r be the return value):
	// for i in [0,r) a[i] < value
	// for i in [r,a.length) a[i] >= value
	public static <T extends Comparable<T>> int lowerBound(T[] a, T value) {
		checkArguments(a, true);
		assert ArrayUtils.isSorted(a);
		return lowerBound(a, 0, a.length, value);
	}

	// upperBound returns the index of the first (lowest-index)
	// element of sub-array a[lo,hi) that is strictly greater than
	// the given value.
	// Preconditions:
	// a != null
	// 0 <= lo <= a.length
	// 0 <= hi <= a.length
	// hi - lo > 0
	// Postconditions (let r be the return value):
	// for i in [lo,r) a[i] <= value
	// for i in [r,hi) a[i] > value
	public static <T> int upperBound(T[] a, int lo, int hi, T value, Comparator<T> c) {
		checkArguments(a, lo, hi, true);
		assert ArrayUtils.isSorted(a, lo, hi, c);
		// In all invariants, lo' and hi' are the original values
		// of lo and hi.
		// Note that due to the invariant that mid is in [lo,hi),
		// we will never get lo > hi.
		while (lo < hi) {
			// In all invariants, lo' and hi' are the original values
			// of lo and hi.
			// for all j in [lo',lo) a[j] <= value
			// for all j in [hi,hi') a[j] > value
			int mid = lo + (hi - lo) / 2;
			// mid in [lo,hi)
			if (c.compare(a[mid], value) <= 0)
				lo = mid + 1;
			// for all j in [lo',lo) a[j] <= value
			else
				hi = mid;
			// for all j in [hi,hi') a[j] > value
		}
		// for all j in [lo',hi) a[j] <= value
		// for all j in [hi,hi') a[j] > value
		return hi;
	}

	// upperBound returns the index of the first (lowest-index)
	// element of array 'a' that is strictly greater than the
	// given value.
	// Preconditions:
	// a != null
	// a.length > 0
	// Postconditions (let r be the return value):
	// for i in [0,r) a[i] <= value
	// for i in [r,a.length) a[i] > value
	public static <T> int upperBound(T[] a, T value, Comparator<T> c) {
		checkArguments(a, true);
		assert ArrayUtils.isSorted(a, c);
		return upperBound(a, 0, a.length, value, c);
	}

	// upperBound returns the index of the first (lowest-index)
	// element of sub-array a[lo,hi) that is strictly greater than
	// the given value.
	// Preconditions:
	// a != null
	// 0 <= lo <= a.length
	// 0 <= hi <= a.length
	// hi - lo > 0
	// Postconditions (let r be the return value):
	// for i in [lo,r) a[i] <= value
	// for i in [r,hi) a[i] > value
	public static <T extends Comparable<T>> int upperBound(T[] a, int lo, int hi, T value) {
		checkArguments(a, lo, hi, true);
		assert ArrayUtils.isSorted(a, lo, hi);
		return upperBound(a, lo, hi, value, new WrapComparable<T>());
	}

	// upperBound returns the index of the first (lowest-index)
	// element of array 'a' that is strictly greater than the
	// given value.
	// Preconditions:
	// a != null
	// a.length > 0
	// Postconditions (let r be the return value):
	// for i in [0,r) a[i] <= value
	// for i in [r,a.length) a[i] > value
	public static <T extends Comparable<T>> int upperBound(T[] a, T value) {
		checkArguments(a, true);
		assert ArrayUtils.isSorted(a);
		return upperBound(a, 0, a.length, value);
	}

	public static <T> int countOrdered(T[] a, int lo, int hi, T value, Comparator<T> c) {
		checkArguments(a, lo, hi, true);
		assert ArrayUtils.isSorted(a, c);
		return BinarySearch.upperBound(a, lo, hi, value, c) - BinarySearch.lowerBound(a, lo, hi, value, c);
	}

	public static <T> int countOrdered(T[] a, T value, Comparator<T> c) {
		checkArguments(a, true);
		assert ArrayUtils.isSorted(a, c);
		return BinarySearch.upperBound(a, value, c) - BinarySearch.lowerBound(a, value, c);
	}

	public static <T extends Comparable<T>> int countOrdered(T[] a, int lo, int hi, T value) {
		checkArguments(a, lo, hi, true);
		assert ArrayUtils.isSorted(a);
		return BinarySearch.upperBound(a, lo, hi, value) - BinarySearch.lowerBound(a, lo, hi, value);
	}

	public static <T extends Comparable<T>> int countOrdered(T[] a, T value) {
		checkArguments(a, true);
		assert ArrayUtils.isSorted(a);
		return BinarySearch.upperBound(a, value) - BinarySearch.lowerBound(a, value);
	}
}
