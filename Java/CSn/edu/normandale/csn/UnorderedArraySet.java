package edu.normandale.csn;

import java.util.Iterator;

/**
 * ArraySet is a simple implementation of the Set API using
 * mostly functions provided by ArrayUtils.  The items
 * in the set are stored in an unsorted array.
 * 
 * We cannot use a sorted array because we have no
 * requirement that T be Comparable.
 * 
 * The complexities of the Set operations are:
 * 		add - O(N)
 * 		contains - O(N)
 * 		remove - O(N)
 * 
 * @author rdwells
 *
 * @param <T>
 */
public class UnorderedArraySet<T> implements Set<T> {

	private T[] set;
	private int numItems = 0;

	final private int minSize = 4;

	@SuppressWarnings("unchecked")
	public UnorderedArraySet() {
		set = (T[]) new Object[minSize];
	}

	@SuppressWarnings("unchecked")
	private void resizeArray(int newSize) {
		T[] newSet = (T[]) new Object[newSize];
		ArrayUtils.move(set, newSet, 0, numItems);
		set = newSet;
	}
	
	@Override
	public void add(T item) {
		
		// If the item is already present, do nothing.
		int idx = ArrayUtils.find(set, item);
		if (idx != -1) return;
		
		// Otherwise, resize the array if necessary,
		// and add this item at the end.
		if (numItems == set.length)
			resizeArray(set.length * 2);
		set[numItems++] = item;
	}

	@Override
	public boolean contains(T item) {
		return ArrayUtils.find(set, item) != -1;
	}

	@Override
	public void remove(T item) {

		// If the item not present in the set, do nothing.
		int idx = ArrayUtils.find(set, item);
		if (idx == -1) return;

		ArrayUtils.rotateLeft(set, idx, numItems);
		set[--numItems] = null;
	}

	@Override
	public int size() {
		return numItems;
	}

	@Override
	public boolean isEmpty() {
		return numItems == 0;
	}

	private class SetIterator implements Iterator<T> {

		int nextIdx = 0;

		@Override
		public boolean hasNext() {
			return nextIdx != numItems;
		}

		@Override
		public T next() {
			return set[nextIdx++];
		}
		
	}
	@Override
	public Iterator<T> iterator() {
		return new SetIterator();
	}
}
