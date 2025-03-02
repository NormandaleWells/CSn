package edu.normandale.csn;

import java.util.Iterator;
import java.util.NoSuchElementException;

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
public class OrderedArraySet<T extends Comparable<T>> implements OrderedSet<T> {

	private T[] set;
	private int numItems = 0;

	final private int minSize = 4;

	@SuppressWarnings("unchecked")
	public OrderedArraySet() {
		set = (T[]) new Comparable[minSize];
	}

	@SuppressWarnings("unchecked")
	private void resizeArray(int newSize) {
		T[] newSet = (T[]) new Comparable[newSize];
		ArrayUtils.move(set, newSet, 0, numItems);
		set = newSet;
	}
	
	@Override
	public void add(T item) {
		
		// If the item is already present, do nothing.0
		int idx = BinarySearch.lowerBound(set, 0, numItems, item);
        if (idx != numItems && set[idx].equals(item)) {
            return;
        }
		
		// Otherwise, resize the array if necessary,
		// and add this item at the end and rotate it
        // into position.
		if (numItems == set.length)
			resizeArray(set.length * 2);
		set[numItems++] = item;
        ArrayUtils.rotateRight(set, idx, numItems);
	}

	@Override
	public boolean contains(T item) {
		return BinarySearch.index(set, 0, numItems, item) != -1;
	}

	@Override
	public void remove(T item) {

		// If the item not present in the set, do nothing.
		int idx = BinarySearch.lowerBound(set, 0, numItems, item);
        if (idx == numItems || !set[idx].equals(item)) {
            return;
        }

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

    @Override
    public T min() {
        if (numItems == 0) {
            throw new NoSuchElementException();
        }
        return set[0];
    }

    @Override
    public T max() {
        if (numItems == 0) {
            throw new NoSuchElementException();
        }
        return set[numItems-1];
    }

    @Override
    public int index(T item) {
        return BinarySearch.lowerBound(set, 0, numItems, item);
    }

    @Override
    public T select(int idx) {
        if (numItems == 0) {
            throw new NoSuchElementException();
        }
        return set[idx];
    }

    @Override
    public T lowerBound(T item) {
		int idx = BinarySearch.lowerBound(set, 0, numItems, item);
        return idx == numItems ? null : set[idx];
    }

    @Override
    public T upperBound(T item) {
		int idx = BinarySearch.upperBound(set, 0, numItems, item);
        return idx == numItems ? null : set[idx];
    }

    @Override
    public Iterator<T> items(T lo, T hi) {
        int loIdx = BinarySearch.lowerBound(set, 0, numItems, lo);
        int hiIdx = BinarySearch.upperBound(set, 0, numItems, hi);
        return ArrayUtils.getForwardIterator(set, loIdx, hiIdx);
    }

	@Override
	public Iterator<T> iterator() {
		return ArrayUtils.getForwardIterator(set, 0, numItems);
	}
	
	public static void main(String[] args) {
		Set<String> set = new OrderedArraySet<>();
		InteractiveCollectionTests.testSet(set);
	}
}
