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
			System.out.printf("%s,%d,%d", name, total, k);
			runOneSize(total, numbers, k, pq);
			System.out.println();
		}
	}

	private static void runTests(String impls, int total, int[] numbers, int[] keep) {
		
		MaxPQ<Integer> pq;

		if (impls.indexOf('l') != -1) {
			pq = new LinkedMaxPQ<Integer>();
			runOnePQ("Linked List", total, numbers, keep, pq);
		}
		
		if (impls.indexOf('u') != -1) {
			pq = new UnorderedArrayMaxPQ<Integer>();
			runOnePQ("Unordered Array", total, numbers, keep, pq);
		}
		
		if (impls.indexOf('o') != -1) {
			pq = new OrderedArrayMaxPQ<Integer>();
			runOnePQ("Ordered Array", total, numbers, keep, pq);
		}
		
		if (impls.indexOf('h') != -1) {
			pq = new HeapMaxPQ<Integer>(1);
			runOnePQ("Heap", total, numbers, keep, pq);
		}
	}

	public static void main(String[] args) {

		if (args.length < 2) {
			System.out.println("usage: ComparePQs <impls> <total> <keep>...");
			System.out.println("    <impl> - list of PQ types to test");
			System.out.println("        l = linked list");
			System.out.println("        u = unordered array");
			System.out.println("        o = ordered array");
			System.out.println("        h = heap");
			System.out.println("    <total> - size of PQ to test");
			System.out.println("    <keep> - number of elements to keep with each test");
			System.out.println("<keep> items are added to the queue, then <total> inserts and deletes are performed.");
			return;
		}

		String impls = args[0];

		int total = Integer.parseInt(args[1]);

		int[] keep = new int[args.length-2];
		for (int i = 2; i < args.length; i++)
			keep[i-2] = Integer.parseInt(args[i]);

		// We need enough numbers for us to be able to add "keep" numbers
		// to the queue before we start the time, then time "total"
		// add and delMax operations.
		int maxIdx = IntArrayUtils.maxElement(keep);
		int[] numbers = new int[total+keep[maxIdx]];

		RandomUtils.setSeed(0);
		for (int i = 0; i < numbers.length; i++)
			numbers[i] = RandomUtils.uniform(-total *2, total * 2);

		runTests(impls, total, numbers, keep);
	}
}
