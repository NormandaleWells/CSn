package edu.normandale.csn;

public class ComparePQs {

	private static double runOneTest(int[] numbers, int keep, MaxPQ<Integer> pq) {

		Timer timer = new Timer();

		for (int i = 0; i < numbers.length; i++) {
			pq.insert(numbers[i]);
			if (pq.size() > keep)
				pq.delMax();
		}
		
		return timer.elapsed();
	}
	
	private static void runOneSize(int[] numbers, int keep, MaxPQ<Integer> pq) {

		// Experience shows that the first run is often an
		// outlier.  I suspect this is due to cache effects.
		runOneTest(numbers, keep, pq);

		for (int run = 0; run < 5; run++)
		{
			double t = runOneTest(numbers, keep, pq);
			System.out.printf(",%.3f", t);
		}
	}

	private static void runOnePQ(String name, int[] numbers, int keep[], MaxPQ<Integer> pq) {

		for (int k : keep) {
			System.out.printf("%s,%d", name, k);
			runOneSize(numbers, k, pq);
			System.out.println();
		}
	}

	private static void runTests(int[] numbers, int[] keep) {
		
		MaxPQ<Integer> pq;

		pq = new LinkedMaxPQ<Integer>();
		runOnePQ("Linked List", numbers, keep, pq);
		
		pq = new ArrayMaxPQ<Integer>();
		runOnePQ("Ordered Array", numbers, keep, pq);
		
		pq = new HeapMaxPQ<Integer>(1);
		runOnePQ("Heap", numbers, keep, pq);
	}

	public static void main(String[] args) {

		if (args.length < 2) {
			System.out.println("usage: ComparePQs <total> <keep>...");
			return;
		}

		int nTotal = Integer.parseInt(args[0]);
		int[] numbers = new int[nTotal];
		RandomUtils.setSeed(0);
		for (int i = 0; i < numbers.length; i++)
			numbers[i] = RandomUtils.uniform(-nTotal *2, nTotal * 2);

		int[] keep = new int[args.length-1];
		for (int i = 1; i < args.length; i++)
			keep[i-1] = Integer.parseInt(args[i]);

		runTests(numbers, keep);
	}
}
