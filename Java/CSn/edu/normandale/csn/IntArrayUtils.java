package edu.normandale.csn;

import java.util.function.Predicate;

public final class IntArrayUtils {

	// Don't allow an object of this type to be created.
	private IntArrayUtils() {
	}

	// All methods here have a precondition that no array passed
	// in as an argument many be null, and for all methods that
	// work with a [lo,hi) range, the lo and hi parameters must
	// be within range.

	// checkArgument() is used to check that an array argument
	// is non-null
	private static boolean checkArguments(int[] a, boolean canBeEmpty) {
		assert a != null;
		assert canBeEmpty || a.length != 0;
		return true;
	}

	// checkArgument() is used to check that an array argument
	// is non-null and that the hi and lo arguments are
	// within range.  If canBeEmpty is false, it also checks
	// that the given range is not empty.
	private static boolean checkArguments(int[] a, int lo, int hi, boolean canBeEmpty) {
		assert a != null;
		assert !canBeEmpty || hi - lo > 0;
		assert lo >= 0 && lo <= a.length;
		assert hi >= 0 && hi <= a.length;
		return true;
	}

	// isEqual is a predicate class that simply checks for
	// equality with a given value.  This allows us to
	// treat equality the same as any other predicate in
	// the find() and count() algorithms.
	private static class IsEqual implements Predicate<Integer> {
		private int value;
		public IsEqual(int value) {
			this.value = value;
		}
		@Override
		public boolean test(Integer t) {
			return t.equals(value);
		}
	}

	// find() returns the index of the first element of a[lo,hi) for
	// which the predicate 'p' returns true;
	public static int find(int[] a, int lo, int hi, Predicate<Integer> p) {
		checkArguments(a, lo, hi, true);
		for (int i = lo; i < hi; i++)
			if (p.test(a[i]))
				return i;
		return -1;
	}

	// find() returns the index of the first element of array 'a'
	// for which the predicate 'p' returns true.
	public static int find(int[] a, Predicate<Integer> p) {
		checkArguments(a, true);
		return find(a, 0, a.length, p);
	}

	// find() returns the index of the first element of a[lo,hi)
	// which matches the given value.
	public static int find(int[] a, int lo, int hi, int value) {
		checkArguments(a, lo, hi, true);
		return find(a, lo, hi, new IsEqual(value));
	}

	// find() returns the index of the first element of array 'a'
	// which matches the given value.
	public static int find(int[] a, int value) {
		checkArguments(a, true);
		return find(a, 0, a.length, value);
	}

	// count() returns the number of elements of a[lo,hi) for
	// which the predicate 'p' returns true.
	public static int count(int[] a, int lo, int hi, Predicate<Integer> p) {
		checkArguments(a, lo, hi, true);
		int count = 0;
		for (int i = lo; i < hi; i++)
			if (p.test(a[i]))
				count++;
		return count;
	}

	// count() returns the number of elements of array 'a'
	// for which the predicate 'p' returns true;
	public static int count(int[] a, Predicate<Integer> p) {
		checkArguments(a, true);
		return count(a, 0, a.length, p);
	}

	// count() returns the number of elements of a[lo,hi) that
	// are equal to 'value'.
	public static int count(int[] a, int lo, int hi, int value) {
		checkArguments(a, lo, hi, true);
		return count(a, lo, hi, new IsEqual(value));
	}

	// count returns the number of elements of array 'a' that
	// are equal to 'value'.
	public static int count(int[] a, int value) {
		checkArguments(a, true);
		return count(a, 0, a.length, value);
	}

	// minElement() returns the index of the minimum element
	// of a[lo,hi).  If there are multiple minimum elements,
	// the first one (lowest index) is returned.
	public static int minElement(int[] a, int lo, int hi) {
		checkArguments(a, lo, hi, false);
		int minIndex = lo;
		for (int i = lo+1; i < hi; i++)
			if (a[i] < a[minIndex])
				minIndex = i;
		return minIndex;
	}

	// minElement() returns the index of the minimum element
	// of array 'a'.  If there are multiple minimum elements,
	// the first one (lowest index) is returned.
	public static int minElement(int[] a) {
		checkArguments(a, false);
		return minElement(a, 0, a.length);
	}

	// maxElement() returns the index of the maximum element
	// of a[lo,hi).  If there are multiple maximum elements,
	// the first one (lowest index) is returned.
	public static int maxElement(int[] a, int lo, int hi) {
		checkArguments(a, lo, hi, false);
		int maxIndex = lo;
		for (int i = lo+1; i < hi; i++)
			if (a[i] > a[maxIndex])
				maxIndex = i;
		return maxIndex;
	}

	// maxElement() returns the index of the maximum element
	// of array 'a'.  If there are multiple maximum elements,
	// the first one (lowest index) is returned.
	public static int maxElement(int[] a) {
		checkArguments(a, false);
		return maxElement(a, 0, a.length);
	}

	// swap swaps the values at indices 'idx1' and 'idx2' in
	// the array 'a'.
	public static void swap(int[] a, int idx1, int idx2) {
		checkArguments(a, idx1, idx2, true);
		int t = a[idx1];
		a[idx1] = a[idx2];
		a[idx2] = t;
	}

	// copy copies the subrange aFrom[lo,hi) to aTo[lo,hi).  All
	// other elements of aTo are untouched.
	public static void copy(int[] aFrom, int[] aTo, int lo, int hi) {
		checkArguments(aFrom, lo, hi, true);
		checkArguments(aTo, lo, hi, true);
		for (int i = lo; i < hi; i++)
			aTo[i] = aFrom[i];
	}

	// copy copies the subrange aFrom[loFrom,hiFrom) to
	// aTo[aTo,aTo+(hiFrom-loFrom)).  All other elements
	// of aTo are untouched.
	public static void copy(int[] aFrom, int loFrom, int hiFrom, int[] aTo, int loTo) {
		checkArguments(aFrom, loFrom, hiFrom, true);
		checkArguments(aTo, loTo, loTo + (hiFrom-loFrom), true);
		for (int iFrom = loFrom, iTo = loTo; iFrom < hiFrom; iFrom++, iTo++) {
			aTo[iTo] = aFrom[iFrom];
		}
	}

	// rotateRight() rotates all elements of a[lo,hi) one position
	// to the right.
	public static void rotateRight(int[] a, int lo, int hi) {
		checkArguments(a, lo, hi, false);
		if (hi - lo < 2) return;
		int t = a[hi-1];
		for (int i = hi-2; i >= lo; i--)
			a[i+1] = a[i];
		a[lo] = t;
	}

	// rotateLeft() rotates all elements of a[lo,hi) one position
	// to the left.
	public static void rotateLeft(int[] a, int lo, int hi) {
		checkArguments(a, lo, hi, false);
		if (hi - lo < 2) return;
		int t = a[lo];
		for (int i = lo+1; i < hi; i++)
			a[i-1] = a[i];
		a[hi-1] = t;
	}

	// isOrdered() returns true if the elements of array 'a'
	// are ordered from smallest to largest.
	public static boolean isOrdered(int[] a) {
		for (int i = 1; i < a.length; i++) {
			if (a[i-1] > a[i])
				return false;
		}
		return true;
	}

	// countOrdered() returns the number of elements of the
	// sorted range a[lo,hi) that are equal to the given value.
	public static int countOrdered(int[] a, int lo, int hi, int value) {
		checkArguments(a, lo, hi, true);
		return IntBinarySearch.upperBound(a, lo, hi, value) - IntBinarySearch.lowerBound(a, lo, hi, value);
	}

	// countOrdered() returns the number of elements of the
	// sorted array 'a' that are equal to the given value.
	public static int countOrdered(int[] a, int value) {
		checkArguments(a, false);
		return IntBinarySearch.upperBound(a, value) - IntBinarySearch.lowerBound(a, value);
	}

	public static void selectionSort(int[] a) {
		for (int i = 0; i < a.length; i++) {
			int idx = minElement(a, i, a.length);
			swap(a, i, idx);
		}
	}

	public static void insertionSort(int[] a) {
		for (int i = 1; i < a.length; i++) {
			int idx = IntBinarySearch.upperBound(a, 0, i, a[i]);
			rotateRight(a, idx, i + 1);
		}
	}
	
	public static void main(String[] args) {
		
		int i = 0;
		assert i++ == 0;
		if (i != 1) {
			System.out.println("This needs to be run with -ea.\n");
			return;
		}

		int[] a1 = { 5, 3, 7, 11, 2 };
		int[] a2 = { 2, 3, 3, 5, 7, 7, 7, 11 };
		
		// TODO: write a real JUnit test suite for these
		assert find(a1, 11) ==3;
		assert find(a1, 4) == -1;
		assert count(a2, 7) == 3;
		assert minElement(a1, 1, 5) == 4;
		assert maxElement(a1, 1, 5) == 3;
	}
}
