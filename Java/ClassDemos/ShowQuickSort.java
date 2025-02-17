import edu.princeton.cs.algs4.*;

public class ShowQuickSort {

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

	private static int partition(Integer[] a, int lo, int hi) {
		int i = lo, j = hi;
		Integer p = a[lo];
		while (true)
		{
			while (less(a[++i], p)) if (i == hi-1) break;
			while (less(p, a[--j])) if (j == lo) break;
			if (i >= j) break;
			exch(a, i, j);
		}
		exch(a, lo, j);
		return j;
	}

	private static void sort(Integer[] a, int lo, int hi) {
		if ((hi - lo) <= 1)
			return;
		int j = partition(a, lo, hi);
		sort(a, lo, j);
		sort(a, j + 1, hi);
	}

	public static void Sort(Integer[] a) {
		StdRandom.shuffle(a);
		sort(a, 0, a.length);
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

		// Display the results.
		if (!isSorted(a))
			StdOut.println("Sort failure!");
	}
}
