package edu.normandale.csn;

import java.util.Iterator;

public class ArrayBag<T> implements Bag<T> {

	T[] bag;
	int nItems;

	@SuppressWarnings("unchecked")
	public ArrayBag(int initSize) {
		if (initSize == 0)
			initSize = 1;
		bag = (T[]) new Object[initSize];
		nItems = 0;
	}

	public ArrayBag() {
		this(1);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void add(T item) {

		// Make sure there's room. If there isn't,
		// resize the array to make it twice as big.
		if (nItems == bag.length) {
			T[] newBag = (T[]) new Object[bag.length * 2];
			ArrayUtils.move(bag, newBag, 0, nItems);
			bag = newBag;
		}

		// Add the new item.
		bag[nItems++] = item;
	}

	@Override
	public boolean isEmpty() {
		return nItems == 0;
	}

	@Override
	public int size() {
		return nItems;
	}

	private class ArrayIterator implements Iterator<T> {
		int current = 0;

		@Override
		public boolean hasNext() {
			return current < nItems;
		}

		@Override
		public T next() {
			return bag[current++];
		}
	}

	@Override
	public Iterator<T> iterator() {
		return new ArrayIterator();
	}

	public static void main(String[] args) {
		Bag<String> bag = new ArrayBag<String>(1);
		TestCollectionUtilities.testBag(bag);
	}
}
