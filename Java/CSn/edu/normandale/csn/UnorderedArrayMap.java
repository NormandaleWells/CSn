package edu.normandale.csn;

import java.util.Iterator;

public class UnorderedArrayMap<Key, Value> implements Map<Key, Value> {

	Key[] keys;
	Value[] values;
	int numItems;

	@SuppressWarnings("unchecked")
	public UnorderedArrayMap() {
		keys =   (  Key[]) new Object[1];
		values = (Value[]) new Object[1];
	}

	@SuppressWarnings("unchecked")
	@Override
	public void put(Key k, Value v) {
		
		// Look for this key.
		int idx = ArrayUtils.find(keys, 0, numItems, k);
		
		// If found, just update the value.
		if (idx != -1)
			values[idx] = v;
		
		// Otherwise, idx is where the key belongs.
		else {
			// Resize the keys and values arrays.
			if (numItems == keys.length) {
				int newLen = keys.length * 2;
				Key[]   newKeys   = (  Key[]) new Object[newLen];
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
		}
	}

	@Override
	public Value get(Key k) {
		int idx = ArrayUtils.find(keys, 0, numItems, k);
		return idx == -1 ? null : values[idx];
	}

	@Override
	public boolean contains(Key k) {
		return ArrayUtils.find(keys, 0, numItems, k) != -1;
	}

	@Override
	public void delete(Key k) {
		
		// Look for this key.  If we don't find it, do nothing.
		int idx = ArrayUtils.find(keys, 0, numItems, k);
		if (idx == -1)
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

	private class UnorderedArrayIterable implements Iterable<Key> {

		@Override
		public Iterator<Key> iterator() {
			return ArrayUtils.getForwardIterator(keys, 0, numItems);
		}
	}

	@Override
	public Iterable<Key> keys() {
		return new UnorderedArrayIterable();
	}
}
