//
//	HashAnalysis - program to test hash function distribution
//
//	Example command lines:
//
//		java -cp .;..\CSn HashAnalysis String Dickens.txt 46181 16
//		java -cp .;..\CSn HashAnalysis Point2d 1MPts.txt 250007 18
//

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;

import edu.normandale.csn.Point2d;
import edu.normandale.csn.RandomUtils;
import edu.normandale.csn.ScannerUtils;
import edu.normandale.csn.Timer;

public class HashAnalysis {

	// Adapted from algs4 library (Sedgewick and Wayne)
	private static double mean(int[] a) {
		double sum = 0.0;
		for (int n : a)
			sum += n;
		return sum / a.length;
	}

	// Adapted from algs4 library (Sedgewick and Wayne)
	private static double stddev(int[] a) {
		double avg = mean(a);
		double sum = 0.0;
		for (int n : a)
			sum += (n - avg) * (n - avg);
		double variance = sum / (a.length - 1);
		return Math.sqrt(variance);
	}

	private static <T> void testHash(T[] a, int mDiv, int k) {

		// Create a set of unique strings has two effects:
		//
		//	(1) it eliminates duplicates
		//	(2) it computes the String hashes
		//
		// Before this was done, the first loop (div method) took
		// nearly 3 times as long to process, since it was doing
		// all the hash computations also.  (The Java String
		// type caches the hash code in the String object once
		// it is computed.)

		System.out.println("Creating HashSet");
		HashSet<T> words = new HashSet<T>();
		for (T t : a)
			words.add(t);

		System.out.println("Testing with division method");
		int[] divTable = new int[mDiv];
		Timer timer = new Timer();
		for (T t : words) {
			int idx = (t.hashCode() & 0x7FFFFFFF) % mDiv;
			divTable[idx]++;
		}
		double divElapsed = timer.elapsed();
		
		System.out.println("Testing with multiplication method");
		long magic = 2654435769L;
		int mMul = 1 << k;
		int[] mulTable = new int[mMul];
		timer = new Timer();
		for (T t : words) {
			long h = t.hashCode() * magic;
			int idx = (int)((h >> (32-k)) & (mMul - 1));
			mulTable[idx]++;
		}
		double mulElapsed = timer.elapsed();
		
		System.out.printf("%d objects hashed\n", words.size());
		System.out.printf("Elapsed time:  div %6.2f  mul %6.2f\n", divElapsed, mulElapsed);
		
		System.out.printf("Div method: mean = %10.4f  stddev = %10.4f\n", mean(divTable), stddev(divTable));
		System.out.printf("Mul method: mean = %10.4f  stddev = %10.4f\n", mean(mulTable), stddev(mulTable));
	}

	private static void testString(String filename, int mDiv, int k) throws FileNotFoundException {
		
		FileInputStream fs = new FileInputStream(filename);
		Scanner sc = new Scanner(fs);
		String[] sa = ScannerUtils.readAllStrings(sc);
		testHash(sa, mDiv, k);
		sc.close();
	}

	private static void testPoint2d(String filename, int mDiv, int k) throws FileNotFoundException {
		
		FileInputStream fs = new FileInputStream(filename);
		Scanner sc = new Scanner(fs);
		int numPoints = sc.nextInt();
		Point2d[] pts = new Point2d[numPoints];
		for (int i = 0; i < numPoints; i++) {
			double x = sc.nextDouble();
			double y = sc.nextDouble();
			pts[i] = new Point2d(x, y);
		}
		testHash(pts, mDiv, k);
		sc.close();
	}

	public static void main(String[] args) throws FileNotFoundException {

		if (args.length < 4) {
			System.out.println("usage: java HashAnalysis <type> <filename> <M> <k>");
			System.out.println("    <type> must be one of the supported types");
			System.out.println("    <filename> is the datafile to read");
			System.out.println("    <M> hash table size for division method");
			System.out.println("    <k> lg(M) for multiplicative method");
			return;
		}
		
		String type = args[0];
		String filename = args[1];
		int mDiv = Integer.parseInt(args[2]);
		int k = Integer.parseInt(args[3]);
		 if (type.equals("String"))
			 testString(filename, mDiv, k);
		 else if (type.equals("Point2d"))
			 testPoint2d(filename, mDiv, k);
		 else
			 System.out.println("Unknown type " + type);
	}
}
