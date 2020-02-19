package edu.normandale.csn;

import java.util.Arrays;
import java.util.Random;

// RandomUtils - based on Sedgewick and Wayne's StdRandom class

public class RandomUtils {

	static Random rand = new Random();

	static void setSeed(long seed) {
		rand.setSeed(seed);
	}

	// Returns a value in the half-open [lo,hi).
	static int uniform(int lo, int hi) {
		return lo + (int)(rand.nextDouble() * (hi - lo));
	}
	
	static int uniform(int n) {
		return uniform(0, n);
	}

	static boolean bernoulli(double p) {
		return rand.nextDouble() < p;
	}
	
	// Adapted from Sedgewick and Wayne's _Algorithms 4th Edition_, page 32.
	static <T> void shuffle(T[] a) {
		int n = a.length;
		for (int i = 0; i < n; i++) {
			int r = i + uniform(n-i);
			T t = a[i];
			a[i] = a[r];
			a[r] = t;
		}
	}

	public static void main(String[] args) {
		
		Integer[] test = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		Integer[] a1 = new Integer[test.length];
		Integer[] a2 = new Integer[test.length];

		ArrayUtils.copy(test, a1, 0, test.length);
		ArrayUtils.copy(test, a2, 0, test.length);
		
		setSeed(0);
		shuffle(a1);
//		TestUtils.printArray(a1);
//		System.out.println();

		setSeed(0);
		shuffle(a2);
//		TestUtils.printArray(a2);
//		System.out.println();

		// Equal seeds should give equal shuffles.
		assert(TestUtils.countEqual(a1, a2) == a1.length);

		Arrays.sort(a1);
		Arrays.sort(a2);
		
		setSeed(1);
		shuffle(a1);
//		TestUtils.printArray(a1);
//		System.out.println();

		setSeed(2);
		shuffle(a2);
//		TestUtils.printArray(a2);
//		System.out.println();

		// Unequal seeds should give unequal shuffles.
		assert(TestUtils.countEqual(a1, a2) != a1.length);
	}

}
