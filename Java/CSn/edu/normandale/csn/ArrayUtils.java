package edu.normandale.csn;

import java.util.Comparator;
import java.util.Iterator;
import java.util.function.Predicate;

public final class ArrayUtils {

	// Don't allow an object of this type to be created.
	private ArrayUtils() {
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

	// find(a, lo, hi, p) returns the index of the first element in
	// the unordered sub-array a[lo,hi) for which the predicate 'p'
	// returns true. Returns -1 if the predicate is false for all
	// elements of a[lo,hi).
	// Preconditions:
	//		a != null or lo == hi
	//		a[lo,hi) != null
	//		0 <= lo <= hi <= a.length
	// Postconditions (let 'r' be the return value):
	//		(r == -1) and (!p(a[lo,hi)))
	//			or
	//		(r != -1) and lo <= r < hi and (p(a[r])) and !p(a[lo,r)))
	public static <T> int find(T[] a, int lo, int hi, Predicate<T> p) {
		checkArguments(a, lo, hi, true);
		for (int i = lo; i < hi; i++)
			// !p(a[lo,i))
			if (p.test(a[i]))
				// !p(a[lo,i)) and p(a[i])
				return i;
		// !p(a[lo,hi))
		return -1;
	}

	// find(a, p) returns the index of the first element of
	// the unordered array 'a' for which the predicate returns
	// true. Returns -1 if the predicate is false for all
	// elements of 'a'.
	// Preconditions:
	//		a != null
	//		a[0,a.length) != null
	// Postconditions (let 'r' be the return value):
	//		(r == -1) and !p(a[0,a.length))
	//			or
	//		(r != -1) and 0 <= r < a.length and (p(a[r])) and !p(a[0,r)))
	public static <T> int find(T[] a, Predicate<T> p) {
		checkArguments(a, true);
		return find(a, 0, a.length, p);
	}

	// find(a, lo, hi, value) returns the index of the first element in
	// the unordered sub-array a[lo,hi) that matches 'value'. Returns -1
	// if 'value' is not found in a[lo,hi).
	// Preconditions:
	//		a != null or lo == hi
	//		a[lo,hi) != null
	//		0 <= lo <= hi <= a.length
	// Postconditions (let 'r' be the return value):
	//		(r == -1) and a[lo,hi) != value)
	//			or
	//		(r != -1) and 0 <= r < a.length and (a[r] == value) and a[lo,r) != value)
	public static <T> int find(T[] a, int lo, int hi, T value) {
		checkArguments(a, lo, hi, true);
		return find(a, lo, hi, new IsEqual<T>(value));
	}

	// find(a, p) returns the index of the first
	// occurrence of 'value' in the unordered array 'a'.
	// Returns -1 if 'value' is not found.
	// Preconditions:
	//		a != null
	//		a[0,a.length) != null
	// Postconditions (let 'r' be the return value):
	//		(r == -1) and (a[0,a.length) != value)
	//			or
	//		(r != -1) and 0 <= r < a.length and (a[r] == value) and (a[0,r) != value)
	public static <T> int find(T[] a, T value) {
		checkArguments(a, true);
		return find(a, 0, a.length, value);
	}

	// count(a, lo, hi, p) returns the number of elements of the
	// unordered subarray a[lo,hi) for which the predicate 'p'
	// returns true.
	// Preconditions:
	//		a != null or lo == hi
	//		a[lo,hi) != null
	//		0 <= lo <= hi <= a.length
	// Postconditions (let 'r' be the return value):
	//		p(a[i] is true for r values in [lo,hi)
	public static <T> int count(T[] a, int lo, int hi, Predicate<T> p) {
		checkArguments(a, lo, hi, true);
		int count = 0;
		for (int i = lo; i < hi; i++)
			// p(a[j]) is true for 'count' items in a[lo,i)
			if (p.test(a[i]))
				count++;
		// p(a[j]) is true for 'count' times in a[lo,hi)
		return count;
	}

	// count(a, p) returns the number of elements of the unordered
	// array 'a' for which the predicate 'p' returns true.
	// Preconditions:
	//		a != null
	//		a[0,a.length) != null
	// Postconditions (let 'r' be the return value):
	//		p(a[i] is true for r values in [0,a.length)
	public static <T> int count(T[] a, Predicate<T> p) {
		checkArguments(a, true);
		return count(a, 0, a.length, p);
	}

	// count(a, lo, hi, p) returns the number of elements of the
	// unordered subarray a[lo,hi) that are equal to 'value'.
	// Preconditions:
	//		a != null or lo == hi
	//		a[lo,hi) != null
	//		0 <= lo <= hi <= a.length
	// Postconditions (let 'r' be the return value):
	//		value occurs r times in the a[lo,hi)
	public static <T> int count(T[] a, int lo, int hi, T value) {
		checkArguments(a, lo, hi, true);
		return count(a, lo, hi, new IsEqual<T>(value));
	}

	// count(a, p) returns the number of elements of the unordered
	// array 'a' that are equal to 'value'.
	// Preconditions:
	//		a != null
	//		a[0,a.length) != null
	// Postconditions (let 'r' be the return value):
	//		value occurs r times in the array
	public static <T> int count(T[] a, T value) {
		checkArguments(a, true);
		return count(a, 0, a.length, value);
	}

	// minElement returns the index of the smallest
	// element of the [lo,hi) subrange of the unordered
	// array 'a'. If there are multiple occurrences of
	// the minimum element, the index of the first
	// (lowest-indexed) one is returned.
	// Preconditions:
	//		a != null
	//		a[lo,hi) != null
	//		0 <= lo <= a.length
	//		0 <= hi <= a.length
	//		hi - lo > 0
	// Postconditions (let 'r' be the return value):
	//		lo <= r < hi
	//		a[lo,r) > a[r]
	//		a[r,hi) >= a[r]
	public static <T> int minElement(T[] a, int lo, int hi, Comparator<T> c) {
		checkArguments(a, lo, hi, false);
		int idxMin = lo;
		for (int i = lo + 1; i < hi; i++)
			// for i in [0,idxMin) a[i] > a[idxMin]
			// for i in [idxMin,i) a[i] >= a[idxMin]
			if (c.compare(a[i], a[idxMin]) < 0)
				idxMin = i;
		// for i in [0,idxMin) a[i] > a[idxMin]
		// for i in [idxMin,i] a[i] >= a[idxMin]
		// for i in [0,idxMin) a[i] > a[idxMin]
		// for i in [idxMin,hi) a[i] >= a[idxMin]
		return idxMin;
	}

	// minElement returns the index of the smallest
	// element of the unordered array 'a'. If there
	// are multiple occurrences of the minimum element,
	// the index of the first (lowest-indexed) one is
	// returned.
	// Preconditions:
	//		a != null
	//		a[0,a.length) != null
	//		a.length > 0
	// Postconditions (let 'r' be the return value):
	//		for i in [0,r) a[i] > a[r]
	//		for i in [r,a.length) a[i] >= a[r]
	public static <T> int minElement(T[] a, Comparator<T> c) {
		checkArguments(a, false);
		return minElement(a, 0, a.length, c);
	}

	// minElement returns the index of the smallest
	// element of the [lo,hi) subrange of the unordered
	// array 'a'. If there are multiple occurrences of
	// the minimum element, the index of the first
	// (lowest-indexed) one is returned.
	// Preconditions:
	// a != null
	// 0 <= lo <= a.length
	// 0 <= hi <= a.length
	// hi - lo > 0
	// Postconditions (let 'r' be the return value):
	// for i in [lo,r) a[i] > a[r]
	// for i in [r,hi) a[i] >= a[r]
	public static <T extends Comparable<T>> int minElement(T[] a, int lo, int hi) {
		checkArguments(a, lo, hi, false);
		return minElement(a, lo, hi, new WrapComparable<T>());
	}

	// minElement returns the index of the smallest
	// element of the unordered array 'a'. If there
	// are multiple occurrences of the minimum element,
	// the index of the first (lowest-indexed) one is
	// returned.
	// Preconditions:
	// a != null
	// a.length > 0
	// Postconditions (let 'r' be the return value):
	// for i in [0,r) a[i] > a[r]
	// for i in [r,a.length) a[i] >= a[r]
	public static <T extends Comparable<T>> int minElement(T[] a) {
		checkArguments(a, false);
		return minElement(a, 0, a.length);
	}

	// maxElement returns the index of the largest
	// element of the [lo,hi) subrange of the unordered
	// array 'a'. If there are multiple occurrences of
	// the maximum element, the index of the first
	// (lowest-indexed) one is returned.
	// Preconditions:
	// a != null
	// 0 <= lo <= a.length
	// 0 <= hi <= a.length
	// hi - lo > 0
	// Postconditions (let 'r' be the return value):
	// for i in [lo,r) a[i] < a[r]
	// for i in [r,hi) a[i] <= a[r]
	public static <T> int maxElement(T[] a, int lo, int hi, Comparator<T> c) {
		checkArguments(a, lo, hi, false);
		int idxMax = lo;
		for (int i = lo + 1; i < hi; i++)
			// for i in [0,idxMin) a[i] < a[idxMax]
			// for i in [idxMin,i) a[i] <= a[idxMax]
			if (c.compare(a[i], a[idxMax]) > 0)
				idxMax = i;
		// for i in [0,idxMin) a[i] < a[idxMax]
		// for i in [idxMin,i] a[i] <= a[idxMax]
		// for i in [0,idxMin) a[i] < a[idxMax]
		// for i in [idxMin,hi) a[i] <= a[idxMax]
		return idxMax;
	}

	// maxElement returns the index of the largest
	// element of the unordered array 'a'. If there
	// are multiple occurrences of the maximum element,
	// the index of the first (lowest-indexed) one is
	// returned
	// Preconditions:
	// a != null
	// a.length > 0
	// Postconditions (let 'r' be the return value):
	// for i in [0,r) a[i] < a[r]
	// for i in [r,a.length) a[i] <= a[r]
	public static <T> int maxElement(T[] a, Comparator<T> c) {
		checkArguments(a, false);
		return maxElement(a, 0, a.length, c);
	}

	// maxElement returns the index of the largest
	// element of the [lo,hi) subrange of the unordered
	// array 'a'. If there are multiple occurrences of
	// the maximum element, the index of the first
	// (lowest-indexed) one is returned.
	// Preconditions:
	// a != null
	// 0 <= lo <= a.length
	// 0 <= hi <= a.length
	// hi - lo > 0
	// Postconditions (let 'r' be the return value):
	// for i in [lo,r) a[i] < a[r]
	// for i in [r,hi) a[i] <= a[r]
	public static <T extends Comparable<T>> int maxElement(T[] a, int lo, int hi) {
		checkArguments(a, lo, hi, false);
		return maxElement(a, lo, hi, new WrapComparable<T>());
	}

	// maxElement returns the index of the largest
	// element of the unordered array 'a'. If there
	// are multiple occurrences of the maximum element,
	// the index of the first (lowest-indexed) one is
	// returned
	// Preconditions:
	// a != null
	// a.length > 0
	// Postconditions (let 'r' be the return value):
	// for i in [0,r) a[i] < a[r]
	// for i in [r,a.length) a[i] <= a[r]
	public static <T extends Comparable<T>> int maxElement(T[] a) {
		checkArguments(a, false);
		return maxElement(a, 0, a.length);
	}

	// swap exchanges the values at indices idx1 and idx2
	// in array a.
	// Preconditions:
	// a != null
	// a.length > 0
	// 0 <= idx1 < a.length
	// 0 <= idx2 < a.length
	// Postconditions (let a' be the original array):
	// for i in [0, length)
	// if i not in {idx1,idx2} a[i] = a'[i]
	// a[idx1] = a'[idx2]
	// a[idx2] = a'[idx1]
	public static <T> void swap(T[] a, int idx1, int idx2) {
		checkArguments(a, false);
		T t = a[idx1];
		a[idx1] = a[idx2];
		a[idx2] = t;
	}

	// copy copies the subrange aFrom[lo,hi) to aTo[lo,hi). All
	// other elements of aTo are untouched.  No elements of a
	// are modified.
	// Preconditions:
	//		aFrom != null
	//		0 <= lo <= hi <= aFrom.length <= aTo.length
	//		aTo != null
	// Postconditions (let aTo' and aFrom' be the original aTo and aFrom):
	//		for i in [0,a.length) aFrom[i] == aFrom'[i]
	//		for i in [0,lo) aTo[i] = aTo'[i]
	//		for i in [lo,hi) aTo[i] = aFrom[i]
	//		for i in [hi,aTo.length) aTo[i] = aTo'[i]
	public static <T> void copy(T[] aFrom, T[] aTo, int lo, int hi) {
		checkArguments(aFrom, lo, hi, true);
		checkArguments(aTo, lo, hi, true);
		for (int i = lo; i < hi; i++)
			aTo[i] = aFrom[i];
	}

	// copy copies the subrange aFrom[loFrom,hiFrom) to
	// aTo[aTo,aTo+(hiFrom-loFrom)). All other elements
	// of aTo are untouched.  No elements of a are
	// modified.
	// Preconditions:
	//		aFrom != null
	//		0 <= loFrom <= hiFrom <= aFrom.length
	//		aTo != null
	//		0 <= loTo <= loTo+(hiFrom-loFrom) <= aTo.length
	// Postconditions (let aTo' and aFrom' be the original aTo and aFrom):
	//		let hiTo = loTo + (hiFrom - loFrom)
	//		for i in [0,a.length) aFrom[i] == aFrom'[i]
	//		for i in [0,loTo) aTo[i] = aTo'[i]
	//		for i in [loTo,hiTo) aTo[i] = aFrom[i]
	//		for i in [hiTo,aTo.length) aTo[i] = aTo'[i]
	public static <T> void copy(T[] aFrom, int loFrom, int hiFrom, T[] aTo, int loTo) {
		checkArguments(aFrom, loFrom, hiFrom, true);
		checkArguments(aTo, loTo, loTo + (hiFrom - loFrom), true);
		for (int iFrom = loFrom, iTo = loTo; iFrom < hiFrom; iFrom++, iTo++) {
			aTo[iTo] = aFrom[iFrom];
		}
	}

	/**
	 * Moves <tt>aFrom[loFrom,hiFrom)</tt> to <tt>aTo[loTo,loTo+(hiFrom-hTo))</tt>.
	 * The elements in aFrom are set to null.
	 * 
	 * @param aFrom
	 *			the array to move from
	 * @param loFrom
	 * 			the low index of the source range
	 * @param hiFrom
	 * 			the high index of the source range
	 * @param aTo
	 *			the array to move to
	 * @param loTo
	 *			the low index of the target range
	 */
	public static <T> void move(T[] aFrom, int loFrom, int hiFrom, T[] aTo, int loTo) {
		
		// Check preconditions
		checkArguments(aFrom, loFrom, hiFrom, true);
		checkArguments(aTo, loTo, loTo + (hiFrom - loFrom), true);

		while (loFrom < hiFrom) {
			aTo[loTo++] = aFrom[loFrom];
			aFrom[loFrom++] = null;
		}
	}

	/**
	 * Moves <tt>aFrom[lo,hi)</tt> to <tt>aTo[lo,hi)</tt>.  The elements in
	 * aFrom are set to null.
	 * 
	 * @param aFrom
	 *            the array to move from
	 * @param aTo
	 *            the array to move to
	 * @param lo
	 *            the start of the range to copy
	 * @param hi
	 *            the end of the range to copy
	 */
	public static <T> void move(T[] aFrom, T[] aTo, int lo, int hi) {
		
		// Check preconditions
		checkArguments(aFrom, lo, hi, true);
		checkArguments(aTo, lo, hi, true);

		move(aFrom, lo, hi, aTo, lo);
	}

	// rotateRight rotates the array range [lo,hi) one element
	// to the right, with a[hi-1] rotating to a[lo].
	// Preconditions:
	// a != null
	// 0 <= lo <= a.length
	// 0 <= hi <= a.length
	// hi - lo > 0
	// Postconditions (let a' be the original array):
	// for i in [lo+1,hi) a[i] < a'[i-1]
	// a[lo] = a'[hi-1]
	public static <T> void rotateRight(T[] a, int lo, int hi) {
		checkArguments(a, lo, hi, true);
		if (hi - lo < 2)
			return;
		T t = a[hi - 1];
		for (int i = hi - 1; i > lo; i--) {
			a[i] = a[i - 1];
		}
		a[lo] = t;
	}

	// rotateLeft rotates the array range [lo,hi) one element
	// to the left, with a[lo] rotating to a[hi-1].
	// Preconditions:
	// a != null
	// 0 <= lo <= a.length
	// 0 <= hi <= a.length
	// hi - lo > 0
	// Postconditions (let a' be the original array):
	// for i in [lo,hi-1) a[i] < a'[i+1]
	// a[hi-1] = a'[lo]
	public static <T> void rotateLeft(T[] a, int lo, int hi) {
		checkArguments(a, lo, hi, true);
		if (hi - lo < 2)
			return;
		T t = a[lo];
		for (int i = lo + 1; i < hi; i++)
			a[i - 1] = a[i];
		a[hi - 1] = t;
	}

	// transform uses UnaryOp to convert the elements
	// of s[sLo,sHi) to type Res, storing the results
	// in destination array d[dLo,dLo+(sHi-sLo)).
	// Preconditions:
	//		s != null
	//		0 <= sLo <= sHi <= s.length
	//		d != null
	//		0 <= dLo <= dLo+(sHi-sLo) <= d.length
	//		f != null
	// Postconditions:
	//		d[dLo,dLo+(sHi-sLo)) = f(s[sLo,sHi))
	public static <T, Res> void transform(
			T[] s, int sLo, int sHi, 
			Res[] d, int dLo,
			UnaryOp<T, Res> f) {
		int l = sHi - sLo;
		checkArguments(s, sLo, sHi, true);
		checkArguments(d, dLo, dLo+l, true);
		for (int i = 0; i < l; i++)
			d[dLo+i] = f.op(s[sLo+i]);
	}

	// transform uses UnaryOp to convert the elements
	// of s to type Res, storing the results in
	// corresponding elements of destination array d.
	// Preconditions:
	//		s != null
	//		d != null
	//		f != null
	// Postconditions:
	//		d[0,d.length) = f(s[0,s.length))
	public static <T, Res> void transform(T[] s, Res[] d, UnaryOp<T, Res> f) {
		checkArguments(s, true);
		checkArguments(d, true);
		transform(s, 0, s.length, d, 0, f);
	}

	// transform uses BinaryOp to convert the elements
	// of s1[s1Lo,s1Hi) and s2[s2Lo,s2Lo+(s1Hi-s1Lo))
	// to type Res, storing the results in destination
	// array d[dLo,dLo+(sHi-sLo)).
	// Preconditions:
	//		s1 != null
	//		0 <= s1Lo <= s1Hi <= s.length
	//		s2 != null
	//		0 <= s2Lo <= s2Lo+(s1Hi-s1Lo) <= s2.length
	//		d != null
	//		0 <= dLo <= dLo+(s1Hi-s1Lo) <= d.length
	//		f != null
	// Postconditions:
	//		d[dLo,dLo+(sHi-sLo)) = f(s1[s1Lo,s1Hi),s2[s2Lo,s2Lo+(s1Hi-s1Lo))
	public static <T1, T2, Res> void transform(
			T1[] s1, int s1Lo, int s1Hi,
			T2[] s2, int s2Lo,
			Res[] d, int dLo,
			BinaryOp<T1, T2, Res> f) {
		int l = s1Hi - s1Lo;
		checkArguments(s1, s1Lo, s1Hi, true);
		checkArguments(s2, s2Lo, s2Lo+l, true);
		checkArguments(d, dLo, dLo+l, true);
		for (int i = 0; i < l; i++)
			d[dLo+i] = f.op(s1[s1Lo+i], s2[s2Lo+i]);
	}

	// transform uses BinaryOp to convert corresponding
	// elements of s to type Res, storing the results in
	// corresponding elements of destination array d.
	// Preconditions:
	//		s != null
	//		d != null
	//		f != null
	// Postconditions:
	//		d[0,d.length) = f(s[0,s.length))
	public static <T1, T2, Res> void transform(
			T1[] s1, T2[] s2, Res[] d,
			BinaryOp<T1, T2, Res> f) {
		checkArguments(s1, true);
		checkArguments(s2, true);
		checkArguments(d, true);
		transform(s1, 0, s1.length, s2, 0, d, 0, f);
	}

	// isSorted returns true if the given array is ordered according
	// to the given Comparator, and false otherwise.
	public static <T> boolean isSorted(T[] a, int lo, int hi, Comparator<T> c) {
		checkArguments(a, lo, hi, true);
		for (int i = lo+1; i < hi; i++) {
			if (c.compare(a[i - 1], a[i]) > 0)
				return false;
		}
		return true;
	}
	
	public static <T extends Comparable<T>> boolean isSorted(T[] a, int lo, int hi) {
		return isSorted(a, lo, hi, new WrapComparable<T>());
	}

	public static <T> boolean isSorted(T[] a, Comparator<T> c) {
		checkArguments(a, true);
		return isSorted(a, 0, a.length, c);
	}

	public static <T extends Comparable<T>> boolean isSorted(T[] a) {
		return isSorted(a, new WrapComparable<T>());
	}

	public static <T> String toString(T[] a, int lo, int hi) {
		StringBuilder sb = new StringBuilder();
		sb.append("[ ");
		for (int i = lo; i < hi; i++) {
			sb.append(String.format("%s ", a[i]));
		}
		sb.append("]");
		return sb.toString();
	}

	public static <T> String toString(T[] a) {
		return toString(a, 0, a.length);
	}

	// ForwardIterator creates an Iterator for iterating
	// forward through the [lo,hi) range of the given array.
	private static class ForwardIterator<T> implements Iterator<T> {

		T[] a;
		int pos;
		int hi;

		public ForwardIterator(T[] a, int lo, int hi) {
			this.a = a;
			this.pos = lo;
			this.hi = hi;
		}

		@Override
		public boolean hasNext() {
			return pos < hi;
		}

		@Override
		public T next() {
			return a[pos++];
		}
	}

	// getForwardIterator() creates a ForwardIterator object
	// for iterating through the given array from lo to hi.
	public static <T> Iterator<T> getForwardIterator(T[] a, int lo, int hi) {
		return new ForwardIterator<T>(a, lo, hi);
	}

	// BackwardIterator creates an Iterator for iterating
	// backward through the [lo,hi) range of the given array.
	private static class BackwardIterator<T> implements Iterator<T> {

		T[] a;
		int pos;
		int lo;

		public BackwardIterator(T[] a, int lo, int hi) {
			this.a = a;
			this.pos = hi;
			this.lo = lo;
		}

		@Override
		public boolean hasNext() {
			return pos > lo;
		}

		@Override
		public T next() {
			return a[--pos];
		}
	}

	// getBackwardIterator() creates a BackwardIterator object
	// for iterating through the given array from hi to lo.
	public static <T> Iterator<T> getBackwardIterator(T[] a, int lo, int hi) {
		return new BackwardIterator<T>(a, lo, hi);
	}
}
