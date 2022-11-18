package edu.normandale.csn;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class HeapMinPQ<Key extends Comparable<Key>> implements MinPQ<Key> {

	int numItems;
	Key[] pq;

	@SuppressWarnings("unchecked")
	public HeapMinPQ(int max) {

		numItems = 0;
		pq = (Key[]) new Comparable[max];
	}

	@SuppressWarnings("unchecked")
	private void resize(int newSize) {

		Key[] newPQ = (Key[]) new Comparable[newSize];
		ArrayUtils.copy(pq, newPQ, 0, numItems);
		pq = newPQ;
	}

	@Override
	public void insert(Key k) {

		// Check to see if we need to resize the array.
		if (numItems == pq.length) {
			resize(pq.length * 2);
		}
		
		// Add the new item.
		pq[numItems++] = k;
		HeapUtils.swimMin(pq, numItems-1);
	}

	@Override
	public Key min() {

		// Make sure we actually have something.
		if (numItems == 0)
			throw new NoSuchElementException();

		return pq[0];
	}

	@Override
	public Key delMin() {

		// Make sure we actually have something.
		if (numItems == 0)
			throw new NoSuchElementException();

		Key m = pq[0];
		pq[0] = pq[--numItems];
		pq[numItems] = null;	// avoid loitering
		HeapUtils.sinkMin(pq, numItems, 0);

		// See if we can reduce the size of the queue.
		if (pq.length >= 4 && numItems < pq.length / 4) {
			resize(pq.length / 2);
		}

		return m;
	}

	@Override
	public boolean isEmpty() {
		return numItems == 0;
	}

	@Override
	public int size() {
		return numItems;
	}

	public static void main(String[] args) throws FileNotFoundException {

		if (args.length == 0) {
			System.out.println("usage: <N> [<file>]\n");
			System.out.println("    <N> is the number of largest integers to keep.");
			System.out.println("    If <file> is not specified, stdin is used.");
			System.out.println("    A file cannot be used with specifying <N>.");
			System.exit(1);
		}

		int nToKeep = Integer.parseInt(args[0]);
		HeapMinPQ<Integer> pq = new HeapMinPQ<Integer>(nToKeep+1);
		
		Scanner scanner;
		if (args.length >= 2) {
			FileInputStream fs = new FileInputStream(args[1]);
			scanner = new Scanner(fs);
		} else {
			scanner = new Scanner(System.in);
		}
		Integer[] numbers = ScannerUtils.readAllIntegers(scanner);
		scanner.close();

		Timer sw = new Timer();
		for (int i : numbers)
		{
			pq.insert(i);
			if (pq.size() > nToKeep)
				pq.delMin();
		}
		double elapsed = sw.elapsed();
		System.out.printf("Elapsed: %7.3f\n", elapsed);
		while (!pq.isEmpty())
			System.out.print(pq.delMin() + " ");
		System.out.println();
	}
}
