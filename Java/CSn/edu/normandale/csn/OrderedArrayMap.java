package edu.normandale.csn;

import java.util.Iterator;

public class OrderedArrayMap<Key extends Comparable<Key>, Value> implements OrderedMap<Key, Value> {

	Key[] keys;
	Value[] values;
	int numItems;

	@SuppressWarnings("unchecked")
	public OrderedArrayMap() {
		keys =   (  Key[]) new Comparable[1];
		values = (Value[]) new Object[1];
	}

	@SuppressWarnings("unchecked")
	@Override
	public void put(Key k, Value v) {
		
		// Look for this key.  If found, just update
		// the value
		int idx = BinarySearch.lowerBound(keys, 0, numItems, k);
		if ((idx != numItems) && (keys[idx].compareTo(k) == 0))
			values[idx] = v;
		
		// Otherwise, idx is where the key belongs.
		else {
			// Resize the keys and values arrays.
			// TODO: Optimize this to leave room for the
			// new key and value.
			if (numItems == keys.length) {
				int newLen = keys.length * 2;
				Key[]   newKeys   = (  Key[]) new Comparable[newLen];
				Value[] newValues = (Value[]) new Object[newLen];
				ArrayUtils.move(keys,   newKeys,   0, numItems);
				ArrayUtils.move(values, newValues, 0, numItems);
				keys = newKeys;
				values = newValues;
			}
			
			// Insert the new key and value at the end,
			// and rotate them into position.
			keys[numItems]   = k;
			values[numItems] = v;
			numItems++;
			ArrayUtils.rotateRight(keys,   idx, numItems);
			ArrayUtils.rotateRight(values, idx, numItems);
		}
	}

	@Override
	public Value get(Key k) {
		int idx = BinarySearch.lowerBound(keys, 0, numItems, k);
		if (idx == numItems)
			return null;
		return keys[idx].compareTo(k) == 0 ? values[idx] : null;
	}

	@Override
	public boolean contains(Key k) {
		return get(k) != null;
	}

	@Override
	public void delete(Key k) {
		
		// Look for this key.  If we don't find it, do nothing.
		int idx = BinarySearch.lowerBound(keys, 0, numItems, k);
		if ((idx == numItems) || (keys[idx].compareTo(k) != 0))
			return;

		// Rotate this item to the end, and remove it.
		ArrayUtils.rotateLeft(keys,   idx, numItems);
		ArrayUtils.rotateLeft(values, idx, numItems);
		--numItems;

		// Avoid loitering.
		keys[numItems] = null;
		values[numItems] = null;
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
	public Iterable<Key> keys() {
		return null;
//		return ArrayUtils.getForwardIterator(keys, 0, numItems);
	}

	@Override
	public Key min() {
		return numItems == 0 ? null : keys[0];
	}

	@Override
	public Key max() {
		return numItems == 0 ? null : keys[numItems-1];
	}

	@Override
	public Key lowerBound(Key k) {
		int idx = BinarySearch.lowerBound(keys, 0, numItems, k);
		return idx == numItems ? null : keys[idx];
	}

	@Override
	public Key upperBound(Key k) {
		int idx = BinarySearch.upperBound(keys, 0, numItems, k);
		return idx == numItems ? null : keys[idx];
	}

	private class OrderedArrayIterable implements Iterable<Key> {

		Key lo;
		Key hi;
		public OrderedArrayIterable(Key lo, Key hi) {
			this.lo = lo;
			this.hi = hi;
		}

		@Override
		public Iterator<Key> iterator() {
			int idxLo = BinarySearch.lowerBound(keys, 0, numItems, lo);
			int idxHi = BinarySearch.upperBound(keys, 0, numItems, hi);
			return ArrayUtils.getForwardIterator(keys, idxLo, idxHi);
		}
	}
	
	@Override
	public Iterable<Key> keys(Key lo, Key hi) {
		return new OrderedArrayIterable(lo, hi);
	}
}
