package edu.normandale.csn;

import java.util.Iterator;
import java.util.Scanner;

public class ArrayMaxPQ<T extends Comparable<T>> implements MaxPQ<T>, Iterable<T> {

	@SuppressWarnings("unchecked")
	private T[] q = (T[]) new Comparable[4];
	private int count = 0;

	@SuppressWarnings("unchecked")
	private void resize(int newSize) {
		assert(newSize >= count);
		T[] newQ = (T[]) new Comparable[newSize];
		ArrayUtils.move(q, newQ, 0, count);
		q = newQ;
	}

	@Override
	public void insert(T value) {
		if (count == q.length)
			resize(q.length * 2);
		int idx = ArrayUtils.upperBound(q, 0, count, value);
		q[count++] = value;
		ArrayUtils.rotateRight(q, idx, count);
	}

	@Override
	public T max() {
		if (count == 0)
			throw new RuntimeException("OrderedMaxPQ underflow");
		return q[count-1];
	}

	@Override
	public T delMax() {
		if (count == 0)
			throw new RuntimeException("OrderedMaxPQ underflow");
		T max = q[--count];
		if (q.length > 4 && count <= q.length / 4) {
			resize(q.length / 2);
		}
		return max;
	}

	@Override
	public boolean isEmpty() {
		return count == 0;
	}

	@Override
	public int size() {
		return count;
	}

	@Override
	public Iterator<T> iterator() {
		return ArrayUtils.getBackwardIterator(q, 0, count);
	}

	public static void main(String[] args) {

		ArrayMaxPQ<Integer> pq = new ArrayMaxPQ<Integer>();

		Scanner scanner = new Scanner(System.in);
		while (scanner.hasNext()) {
			int i = scanner.nextInt();
			pq.insert(i);
			if (pq.size() > 10)
				pq.delMax();
		}
		scanner.close();

		for (int i : pq) {
			System.out.println(i);
		}
	}
}
