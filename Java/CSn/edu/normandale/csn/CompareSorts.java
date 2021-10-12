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

		if (args.length < 3) {
			System.out.println("usage: CompareSorts <n> <repeat> <sorts>");
			System.out.println("    <n> - number of integers to sort");
			System.out.println("    <repeat> - number of times to repeat each test");
			System.out.println("    <sorts> - list of sorting algorithms to test");
			System.out.println("        s - selection sort");
			System.out.println("        i - insertion sort");
			System.out.println("        m - merge sort");
			System.out.println("        q - quicksort");
			System.exit(0);
		}
		// Get n and m from the command line, and allocate the array.
		int n = Integer.parseInt(args[0]);
		int[] a = new int[n];
		int repeat = Integer.parseInt(args[1]);
		String sorts = args[2];
		
		// Generate a random array.  Note that we set a seed so that
		// we get predictable values each time.
		RandomUtils.setSeed(0);
		for (int i = 0; i < n; i++)
			a[i] = RandomUtils.uniform(0, n);
		
		// Test the sorts.
		if (sorts.contains("s"))
			for (int i = 0; i < repeat; i++)
				timeSelectionsort(a, n);
		if (sorts.contains("i"))
			for (int i = 0; i < repeat; i++)
				timeInsertionsort(a, n);
		if (sorts.contains("m"))
			for (int i = 0; i < repeat; i++)
				timeMergesort(a, n);
		if (sorts.contains("q"))
			for (int i = 0; i < repeat; i++)
				timeQuicksort(a, n);
		System.out.println("Done");
	}
}
