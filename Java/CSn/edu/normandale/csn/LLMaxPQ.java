package edu.normandale.csn;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Scanner;

// It's possible that this could be made a little faster
// by keeping the list sorted in reverse order.  With the
// current code, insert() is O(1) and delMax() is O(N);
// if the list of sorted, insert is O(N) and delMax()
// is O(1), but the insert would only have to search
// (on average) half the list, so it may be twice as
// fast.  But given that the linked list implementation
// is hundreds of times slower (!) than the array version,
// it's just not worth the time.

public class LLMaxPQ<T extends Comparable<T>> implements MaxPQ<T>, Iterable<T> {

	LinkedList<T> q = new LinkedList<T>();
	Comparator<T> comp = new WrapComparable<T>();

	@Override
	public void insert(T value) {
		q.addFront(value);
	}

	@Override
	public T max() {
		T t = q.removeMax(comp);
		q.addFront(t);
		return t;
	}

	@Override
	public T delMax() {
		return q.removeMax(comp);
	}

	@Override
	public boolean isEmpty() {
		return q.isEmpty();
	}

	@Override
	public int size() {
		return q.size();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Iterator<T> iterator() {
		T[] sortedQ = (T[]) new Comparable[q.size()];
		int pos = 0;
		for (T t : q)
			sortedQ[pos++] = t;
		Arrays.sort(sortedQ);
		return ArrayUtils.getBackwardIterator(sortedQ, 0, pos);
	}

	public static void main(String[] args) {

		LLMaxPQ<Integer> pq = new LLMaxPQ<Integer>();
		
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
