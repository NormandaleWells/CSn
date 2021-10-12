
import edu.princeton.cs.algs4.*;

public class ShowHeapSort {

	// Draw the array.
	public static void show(Integer[] a) {
		int n = a.length;
		StdDraw.setCanvasSize(n + 2, n + 2);
		StdDraw.setScale(0, n + 2);
		StdDraw.setPenColor(StdDraw.BLACK);
		// StdDraw.line( 1, 1, n+1, 1);
		// StdDraw.line(n+1, 1, n+1, n+1);
		// StdDraw.line(n+1, n+1, 1, n+1);
		// StdDraw.line( 1, n+1, 1, 1);
		// StdDraw.line( 1, 1, n+1, n+1);
		// StdDraw.line( 1, n+1, n+1, 1);
		for (int i = 0; i < n; i++) {
			StdDraw.line(i + 1, 2, i + 1, a[i] + 2);
		}
	}

	// Draw the lines representing a pair of points.
	public static void showLines(Integer[] a, int idx1, int idx2, boolean draw) {
		StdDraw.setPenColor(draw ? StdDraw.BLACK : StdDraw.WHITE);
		StdDraw.line(idx1 + 1, 2, idx1 + 1, draw ? a[idx1] + 2 : a.length+1);
		StdDraw.line(idx2 + 1, 2, idx2 + 1, draw ? a[idx2] + 2 : a.length+1);
	}

	// Compare two values.
	private static boolean less(Integer a, Integer b) {
		return a.compareTo(b) < 0;
	}

	// Exchange two array entries.
	private static void exch(Integer[] a, int idx1, int idx2) {
		showLines(a, idx1, idx2, false);
		Integer t = a[idx1];
		a[idx1] = a[idx2];
		a[idx2] = t;
		showLines(a, idx1, idx2, true);
	}

	// Check that we're really sorted.
	private static boolean isSorted(Integer[] a) {
		for (int i = 1; i < a.length; i++)
			if (less(a[i], a[i - 1]))
				return false;
		return true;
	}

	public static void sink(Integer[] a, int k, int n) {
		while (true) {
			// Get the index of the left child.
			int j = 2 * k + 1;

			// If that's beyond the heap, we're done.
			if (j >= n)
				break;

			// If the right child exists, and is greater
			// than the left, use that instead.
			if (j + 1 < n && less(a[j], a[j + 1]))
				j++;

			// If the parent is greater than or equal
			// the (greatest) child, we're done.
			if (!less(a[k], a[j]))
				break;

			// Otherwise, we exchange the parent and
			// child, and loop back around to check
			// the child.
			exch(a, k, j);
			k = j;
		}
	}

	public static void Sort(Integer[] a) {

		int n = a.length;

		// Phase 1 - heapification
		// Starting with the last non-leaf entry and working
		// our way to the left and up, sink each successive
		// node to get it where it belongs in the heap.
		for (int k = n / 2 - 1; k >= 0; k--)
			sink(a, k, n);

		// Phase 2 - sort
		// Exchange the last entry with the top, and sink
		// the new top entry down to restore the heap.
		while (n > 1) {
			exch(a, 0, --n);
			sink(a, 0, n);
		}
	}

	public static void main(String[] args) {

		int n = 700;
		Integer[] a = new Integer[n];
		for (int i = 0; i < n; i++)
			a[i] = i;

		StdRandom.shuffle(a);
		show(a);

		// Time the sort operation.
		Sort(a);

		// Verify that it worked.
		if (!isSorted(a))
			StdOut.println("Sort failure!");
	}
}
