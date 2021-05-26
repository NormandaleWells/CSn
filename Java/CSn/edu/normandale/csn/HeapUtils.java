package edu.normandale.csn;

public final class HeapUtils {

	// Don't allow an object of this type to be created.
	private HeapUtils() {
	}

	private static <Key extends Comparable<Key>> boolean less(Key a, Key b) {
		return a.compareTo(b) < 0;
	}

	private static <T extends Comparable<T>> boolean greater(T a, T b) {
		return a.compareTo(b) > 0;
	}

	private static int parent(int k) {
		return (k-1) / 2;
	}
	
	private static int leftChild(int p) {
		return p * 2 + 1;
	}
	
	public static <T extends Comparable<T>> void swimMax(T[] a, int k) {
		int p = parent(k);
		while (k > 0 && less(a[p], a[k])) {
			ArrayUtils.swap(a, p, k);
			k = p;
			p = parent(k);
		}
	}
	
	public static <T extends Comparable<T>> void sinkMax(T[] a, int n, int k) {
		int child = leftChild(k);
		while (child < n) {
			int other = child + 1;
			if (other < n && less(a[child], a[other]))
				child = other;
			if (!less(a[k], a[child])) break;
			ArrayUtils.swap(a, k, child);
			k = child;
			child = leftChild(k);
		}
	}

	public static <T extends Comparable<T>> void swimMin(T[] a, int k) {
		int p = parent(k);
		while (k > 0 && greater(a[p], a[k])) {
			ArrayUtils.swap(a, p, k);
			k = p;
			p = parent(k);
		}
	}
	
	public static <T extends Comparable<T>> void sinkMin(T[] a, int n, int k) {
		int child = leftChild(k);
		while (child < n) {
			int other = child + 1;
			if (other < n && greater(a[child], a[other]))
				child = other;
			if (!greater(a[k], a[child])) break;
			ArrayUtils.swap(a, k, child);
			k = child;
			child = leftChild(k);
		}
	}

}
