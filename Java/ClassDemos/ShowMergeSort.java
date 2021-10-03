
import edu.princeton.cs.algs4.*;

public class ShowMergeSort {

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
	public static void showLine(Integer[] a, int idx, boolean draw) {
		StdDraw.setPenColor(draw ? StdDraw.BLACK : StdDraw.WHITE);
		StdDraw.line(idx + 1, 2, idx + 1, draw ? a[idx] + 2 : a.length+1);
	}

	// Compare two values.
	private static boolean less(Integer a, Integer b) {
		return a.compareTo(b) < 0;
	}

	// Check that we're really sorted.
	private static boolean isSorted(Integer[] a) {
		for (int i = 1; i < a.length; i++)
			if (less(a[i], a[i - 1]))
				return false;
		return true;
	}

	private static Integer[] aux;

	private static void merge(Integer[] a, int lo, int mid, int hi)
	{
		int i = lo, j = mid;
		
		for (int k = lo; k < hi; k++) aux[k] = a[k];
		
		for (int k = lo; k < hi; k++)
		{
			showLine(a, k, false);
			if (i == mid)                  a[k] = aux[j++];
			else if (j == hi)              a[k] = aux[i++];
			else if (less(aux[j], aux[i])) a[k] = aux[j++];
			else                           a[k] = aux[i++];
			showLine(a, k, true);
		}
	}

	private static void sort(Integer[] a, int lo, int hi) {
		if ((hi - lo) <= 1)
			return;
		int mid = lo + (hi - lo) / 2;
		sort(a, lo, mid);
		sort(a, mid, hi);
		merge(a, lo, mid, hi);
	}

	public static void Sort(Integer[] a) {
		aux = new Integer[a.length];
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
