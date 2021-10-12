package edu.normandale.csn;

import java.util.Comparator;
import java.util.function.Predicate;

public class Sort {

	// Useful functions

	private static <T> boolean less(T a, T b, Comparator<T> comp) {
		return comp.compare(a, b) < 0;
	}

	private static <T extends Comparable<T>> boolean less(T a, T b) {
		return a.compareTo(b) < 0;
	}

	// selectionSort and variants

	public static <T> void selectionSort(T[] a, Comparator<T> comp) {
		for (int i = 0; i < a.length; i++) {
			// invariants:
			//		a[0,i) is sorted
			//		for all j in [i,a.length) a[j] >= a[i-1]
			int idx = ArrayUtils.minElement(a, i, a.length, comp);
			ArrayUtils.swap(a, i, idx);
		}
	}

	public static <T extends Comparable<T>> void selectionSort(T[] a) {
		selectionSort(a, new WrapComparable<T>());
	}

		// insertionSort and variants

	public static <T> void insertionSort(T[] a, int lo, int hi, Comparator<T> comp) {
		for (int i = lo+1; i < hi; i++) {
			// invariant: a[lo,i) is sorted
			int idx = ArrayUtils.upperBound(a, lo, i, a[i], comp);
			ArrayUtils.rotateRight(a, idx, i+1);
		}
	}

	public static <T extends Comparable<T>> void insertionSort(T[] a, int lo, int hi) {
		Sort.insertionSort(a, lo, hi, new WrapComparable<T>());
	}

	public static <T extends Comparable<T>> void insertionSort(T[] a, Comparator<T> comp) {
		insertionSort(a, 0, a.length, comp);
	}

	public static <T extends Comparable<T>> void insertionSort(T[] a) {
		insertionSort(a, 0, a.length);
	}

	// mergeSort and variants

	private static <T> void merge(T[] a, T[] aux, int lo, int mid, int hi, Comparator<T> comp) {

		ArrayUtils.copy(a, aux, lo, hi);
		
		for (int i = lo, j = mid, k = lo; k < hi; k++)
			if (i == mid)
				a[k] = aux[j++];
			else if (j == hi)
				a[k] = aux[i++];
			else if (less(aux[j], aux[i], comp))
				a[k] = aux[j++];
			else
				a[k] = aux[i++];
	}

	private static <T> void mergeSort(T[] a, T[] aux, int lo, int hi, Comparator<T> comp) {

		final int cutoff = 20;
		if (hi - lo < cutoff) {
			insertionSort(a, lo, hi, comp);
			return;
		}

		int mid = lo + (hi - lo) / 2;
		mergeSort(a, aux, lo, mid, comp);
		mergeSort(a, aux, mid, hi, comp);
		merge(a, aux, lo, mid, hi, comp);
	}

	@SuppressWarnings("unchecked")
	public static <T> void mergeSort(T[] a, Comparator<T> comp) {
		T[] aux = (T[]) new Object[a.length];
		mergeSort(a, aux, 0, a.length, comp);
	}

	public static <T extends Comparable<T>> void mergeSort(T[] a) {
		mergeSort(a, new WrapComparable<T>());
	}

	@SuppressWarnings("unchecked")
	public static <T> void bottomUpMergeSort(T[] a, Comparator<T> comp) {
		T[] aux = (T[]) new Object[a.length];
		for (int sz = 1; sz < a.length; sz = sz+sz)
			for (int lo = 0; lo < a.length-sz; lo += sz+sz)
				merge(a, aux, lo, lo+sz, Math.min(lo+sz+sz, a.length), comp);
	}

	public static <T extends Comparable<T>> void bottomUpMergeSort(T[] a) {
		bottomUpMergeSort(a, new WrapComparable<T>());
	}

	// quickSort and variants

	private static <T extends Comparable<T>> int partition(T[] a, int lo, int hi) {
		// Adapted from Sedgewick and Wayne to use the half-open
		// range a[lo,hi) rather than a closed range.
		int i = lo, j = hi;
		T p = a[lo];
		while (true)
		{
			// Replacing less() with a direct call to compareTo actually slowed
			// down the code.  TODO: figure out why!
			// Important note: we also stop when we find something
			// equal to p; otherwise, we get N^2 behavior with
			// arrays containing all the same value.
			while (less(a[++i], p)) if (i == hi-1) break;
			while (less(p, a[--j])) if (j == lo) break;
			if (i >= j) break;

			// Moving swap() in-line doesn't make this any faster.
			ArrayUtils.swap(a, i, j);
		}
		ArrayUtils.swap(a, lo, j);
		return j;
	}

	private static <T extends Comparable<T>> void quicksort(T[] a, int lo, int hi) {
//		System.out.printf("quicksort([%d,%d],%d,%d)", a[0], a[1], lo, hi);
		if ((hi - lo) <= 1) {
//			System.out.println();
			return;
		}
		int j = partition(a, lo, hi);
//		System.out.printf(" -> %d\n", j);
		quicksort(a, lo, j);
		quicksort(a, j+1, hi);
	}

	public static <T extends Comparable<T>> void quicksort(T[] a) {
		RandomUtils.shuffle(a);
		quicksort(a, 0, a.length);
	}

	// partitions the half-open range a[lo,hi) and returns k
	// such that a[lo,k) < a[k,hi).
	// This code was adapted from the zyBooks Data Structures
	// and Algorithms book.  The main difference is that we're
	// using half-open ranges instead of closed ranges.  This
	// necessitated two main changes:
	//		(1) hi is predecremented
	//		(2) We return lo instead of hi.  This was a subtle
	//		    issue to track down.  It boiled down to the
	//		    difference between the midpoint on a 2-element
	//		    array for a closed range (mid = lo) and a
	//		    half-open range (mid = lo+1).
	private static <T extends Comparable<T>> int partitionZyBooks(T[] a, int lo, int hi) {
		
		int mid = lo + (hi - lo) / 2;
		T p = a[mid];

		while (true) {
			// invariant: a[lo',lo) <= p, a[hi,hi') >= p
			while (less(a[lo], p))
				lo += 1;
			while (less(p, a[--hi]))
				;
			// We know here that a[lo] >= p, and a[hi] <= p.
			// Also, a[hi+1] >= p and a[lo-1] <= p.
			if (hi <= lo)
				break;
			// Swap these values to re-establish the invariant.
			ArrayUtils.swap(a, lo,  hi);
			
			// Increment lo to the next item
			// Note that hi gets pre-decremented in the loop above
			lo += 1;
		}

		// Since we know that a[lo-1] <= p, combining that with
		// our invariant we know that a[lo',lo) <= p.  Also,
		// a[hi] <= p and hi <= lo.
		return lo;
	}

	private static <T extends Comparable<T>> void quicksortZyBooks(T[] a, int lo, int hi) {
		if ((hi - lo) <= 1) {
			System.out.println();
			return;
		}

		int j = partitionZyBooks(a, lo, hi);
		quicksortZyBooks(a, lo, j);
		quicksortZyBooks(a, j, hi);
	}

	public static <T extends Comparable<T>> void quicksortZyBooks(T[] a) {
		RandomUtils.shuffle(a);
		quicksortZyBooks(a, 0, a.length);
	}

	private static <T extends Comparable<T>> int bentleyPartition(T[] a, int lo, int hi) {
		// NOTE: We cannot just partition such that a[lo,i) < p and
		// a[i,n) >= p, because that does not guarantee that we end up
		// with two smaller arrays to sort.  For example, if a[lo] is the
		// smallest item in the range, the we return lo, and recursively
		// sort a[lo,lo) and a[lo,hi).  We need to end up with 1 item in
		// the proper spot.
		// Warning: this gives N^2 behavior on an array that consists
		// entirely of a one repeated value.
		T p = a[lo];
		int i = lo+1;
		int n = i;
		while (n < hi) {
			// Invariant:
			//	a[lo+1,i) < p
			//	a[i,n) >= p
			//	a[n,hi) are unseen
			if (less(a[n], p))
				ArrayUtils.swap(a, i++, n);
			n++;
		}
		ArrayUtils.swap(a, --i, lo);
		return i;
	}

	private static <T extends Comparable<T>> void quicksortBentley(T[] a, int lo, int hi) {
		if (hi - lo <= 1)
			return;

		int idx = bentleyPartition(a, lo, hi);
		quicksortBentley(a, lo, idx);
		quicksortBentley(a, idx+1, hi);
	}

	public static <T extends Comparable<T>> void quicksortBentley(T[] a) {
		RandomUtils.shuffle(a);
		quicksortBentley(a, 0, a.length);
	}

	// nthElement

	private static <T extends Comparable<T>> void nthElement(T[] a, int lo, int hi, int k) {
		while (true) {
			int idx = partition(a, lo, hi);
			if (idx > k)
				hi = idx;
			else if (idx < k)
				lo = idx+1;
			else
				break;
		}
	}

	// nthElement() rearranges the array 'a' so that the k'th
	// element is in the position it would be if the array was
	// sorted.  As a side-effect, it also moves all elements
	// less than that final k'th element to positions before
	// k, and all elements greater than the final k'th element
	// to positions after k.
	// The position is
	//		a[0,k) <= a[k]
	//		a[k+1,N) >= a[k]
	// where N is a.length.
	// nthElement() runs in O(N) time, where N=a.length.
	public static <T extends Comparable<T>> void nthElement(T[] a, int k) {
		RandomUtils.shuffle(a);
		nthElement(a, 0, a.length, k);
	}

	// partition

	public static <T> int partition(T[] a, int lo, int hi, Predicate<T> p) {
		// This is basically the same as the bentleyParitition algorithm
		// above, except that we don't have a partition element, but
		// rather a partition function.
		// The return value is an index idx such that:
		//		for all i in a[lo,idx), p(i) is true
		//		for all i in a[idx,hi), p(i) is false
		// That is, all the elements for which p returns true precede
		// all the ones for which it returns false.
		// This would probably be slightly faster if we used a partitioning
		// scheme like the one used for the normal quicksort algorithm,
		// but I find this much easier to understand.
		// partition() runs in O(N) time, where N=a.length.
		int i = lo;
		int n = lo;
		while (n < hi) {
			// loop invariant:
			//		for all i in a[lo,i) p(i) is true
			//		for all i in a[i,n) p(i) is false
			if (p.test(a[n]))
				ArrayUtils.swap(a, i++, n);
			n++;
		}
		return i;
	}

	// partition() rearranges the array so that everything
	// for which the given predicate returns true comes
	// before all the elements for which it returns false.
	// In other words, the postcondition is:
	//		p(a[0,ret)) == true
	//		p(a[ret,N)) == false
	// where 'ret' is the return value from partition,
	// and 'N' is a.length.
	public static <T> int partition(T[] a, Predicate<T> p) {
		return partition(a, 0, a.length, p);
	}

	// heapsort
	
	public static void heapSort(Integer[] a) {

		int n = a.length;

		// Phase 1 - heapification
		// Starting with the last non-leaf entry and working
		// our way to the left and up, sink each successive
		// node to get it where it belongs in the heap.
		for (int k = n / 2; k >= 0; k--)
			HeapUtils.sinkMax(a, n, k);

		// Phase 2 - sort
		// Exchange the last entry with the top, and sink
		// the new top entry down to restore the heap.
		while (n > 1) {
			ArrayUtils.swap(a, 0, --n);
			HeapUtils.sinkMax(a, n, 0);
		}
	}

	// useful common predicates, mainly for testing

	private static class Even implements Predicate<Integer> {
		@Override
		public boolean test(Integer t) {
			return t % 2 == 0;
		}
	}

	private static class DivisibleBy implements Predicate<Integer> {
		int n;
		public DivisibleBy(int n) {
			this.n = n;
		}
		public boolean test(Integer t) {
			return t % n == 0;
		}
	}

	// test utilities

	private static <T> void printArray(String s, T[] a) {
		System.out.println(s);
		for (int i = 0; i < a.length; i++)
			System.out.print(a[i] + " ");
		System.out.println();
	}

	public static void main(String[] args) {
		
//		Integer[] b = { 4, 3, 2, 1 };
//		quicksortZyBooks(b);
//		System.exit(0);

		Integer[] a = new Integer[31];
		for (int i = 0; i < a.length; i++)
			a[i] = i;

		RandomUtils.shuffle(a);
		selectionSort(a);
		printArray("selectionSort", a);
		
		RandomUtils.shuffle(a);
		insertionSort(a);
		printArray("insertionSort", a);
		
		RandomUtils.shuffle(a);
		mergeSort(a);
		printArray("mergeSort", a);
		
		RandomUtils.shuffle(a);
		bottomUpMergeSort(a);
		printArray("bottomUpMergeSort", a);
		
		RandomUtils.shuffle(a);
		quicksort(a);
		printArray("quickSort", a);

		RandomUtils.shuffle(a);
		quicksortZyBooks(a);
		printArray("quickSortZyBooks", a);

		RandomUtils.shuffle(a);
		quicksortBentley(a);
		printArray("quickSortBentley", a);

		RandomUtils.shuffle(a);
		nthElement(a, 10);
		printArray("nthElement(10)", a);
		
		RandomUtils.shuffle(a);
		nthElement(a, 20);
		printArray("nthElement(20)", a);
		
		RandomUtils.shuffle(a);
		partition(a, new Even());
		printArray("partition(Even)", a);
		
		RandomUtils.shuffle(a);
		partition(a, new DivisibleBy(3));
		printArray("partition(DivisibleBy 3)", a);

		Predicate<Integer> even = (Integer i) -> i % 2 == 0;;

		RandomUtils.shuffle(a);
		partition(a, even);
		printArray("partition(even)", a);
		
		RandomUtils.shuffle(a);
		heapSort(a);
		printArray("heapSort", a);
	}
}
