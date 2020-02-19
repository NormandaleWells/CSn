package edu.normandale.csn;

public class CompareSorts {

	private static void timeInsertionsort(int[] a, int n) {
		Integer[] aInt = new Integer[n];
		for (int i = 0; i < n; i++)
			aInt[i] = a[i];
		Timer sw = new Timer();
		Sort.insertionSort(aInt);
		double elapsed = sw.elapsed();
		if (!ArrayUtils.isSorted(aInt))
			System.out.println("Sort failure!\n");
		System.out.printf("Insertion sort: %d, %7.3f\n", n, elapsed);
	}

	private static void timeSelectionsort(int[] a, int n) {
		Integer[] aInt = new Integer[n];
		for (int i = 0; i < n; i++)
			aInt[i] = a[i];
		Timer sw = new Timer();
		Sort.selectionSort(aInt);
		double elapsed = sw.elapsed();
		if (!ArrayUtils.isSorted(aInt))
			System.out.println("Sort failure!\n");
		System.out.printf("Selection sort: %d, %7.3f\n", n, elapsed);
	}

	private static void timeQuicksort(int[] a, int n) {
		Integer[] aInt = new Integer[n];
		for (int i = 0; i < n; i++)
			aInt[i] = a[i];
		Timer sw = new Timer();
		Sort.quicksort(aInt);
		double elapsed = sw.elapsed();
		if (!ArrayUtils.isSorted(aInt))
			System.out.println("Sort failure!\n");
		System.out.printf("Quicksort : %d, %7.3f\n", n, elapsed);
	}

	private static void timeMergesort(int[] a, int n) {
		Integer[] aInt = new Integer[n];
		for (int i = 0; i < n; i++)
			aInt[i] = a[i];
		Timer sw = new Timer();
		Sort.mergeSort(aInt);
		double elapsed = sw.elapsed();
		if (!ArrayUtils.isSorted(aInt))
			System.out.println("Sort failure!\n");
		System.out.printf("Mergesort : %d, %7.3f\n", n, elapsed);
	}
	
	public static void main(String[] args) {
		
		// Get n and m from the command line, and allocate the array.
		int n = Integer.parseInt(args[0]);
		int[] a = new int[n];
		
		// Generate a random array.  Note that we set a seed so that
		// we get predictable values each time.
		RandomUtils.setSeed(0);
		for (int i = 0; i < n; i++)
			a[i] = RandomUtils.uniform(0, n);
		
		// Test the sorts.
		timeInsertionsort(a, n);
		timeSelectionsort(a, n);
		timeQuicksort(a, n);
		timeMergesort(a, n);
		System.out.println("Done");
	}
}
