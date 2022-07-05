package edu.normandale.csn;

public class ComparePQs {

	private static double runOneTest(int total, int[] numbers, int keep, MaxPQ<Integer> pq) {

		// Add "keep" items to the PQ - these are not timed
		for (int i = 0; i < keep; i++)
			pq.insert(numbers[i]);

		Timer timer = new Timer();

		// Now do "total" insert and delMax operations.
		for (int i = 0; i < total; i++) {
			pq.insert(numbers[keep+i]);
			pq.delMax();
		}
		
		double elapsed = timer.elapsed();

		while (!pq.isEmpty())
			pq.delMax();

		return elapsed;
	}
	
	private static void runOneSize(int total, int[] numbers, int keep, MaxPQ<Integer> pq) {

		// Experience shows that the first run is often an
		// outlier.  I suspect this is due to cache effects.
		runOneTest(total, numbers, keep, pq);

		for (int run = 0; run < 5; run++)
		{
			double t = runOneTest(total, numbers, keep, pq);
			System.out.printf(",%.3f", t);
		}
	}

	private static void runOnePQ(String name, int total, int[] numbers, int keep[], MaxPQ<Integer> pq) {

		for (int k : keep) {
			System.out.printf("%s,%d", name, k);
			runOneSize(total, numbers, k, pq);
			System.out.println();
		}
	}

	private static void runTests(int total, int[] numbers, int[] keep) {
		
		MaxPQ<Integer> pq;

		pq = new LinkedMaxPQ<Integer>();
		runOnePQ("Linked List", total, numbers, keep, pq);
		
		pq = new ArrayMaxPQ<Integer>();
		runOnePQ("Ordered Array", total, numbers, keep, pq);
		
		pq = new HeapMaxPQ<Integer>(1);
		runOnePQ("Heap", total, numbers, keep, pq);
	}

	public static void main(String[] args) {

		if (args.length < 2) {
			System.out.println("usage: ComparePQs <total> <keep>...");
			System.out.println("Times <total> insert() and delMax() operations on PQs of size <keep>");
			return;
		}

		int total = Integer.parseInt(args[0]);

		int[] keep = new int[args.length-1];
		for (int i = 1; i < args.length; i++)
			keep[i-1] = Integer.parseInt(args[i]);

		// We need enough numbers for us to be able to add "keep" numbers
		// to the queue before we start the time, then time "total"
		// add and delMax operations.
		int maxIdx = IntArrayUtils.maxElement(keep);
		int[] numbers = new int[total+keep[maxIdx]];

		RandomUtils.setSeed(0);
		for (int i = 0; i < numbers.length; i++)
			numbers[i] = RandomUtils.uniform(-total *2, total * 2);

		runTests(total, numbers, keep);
	}
}
