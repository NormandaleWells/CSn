package edu.normandale.csn;

import java.util.Iterator;

/*
 * This is a hash table based implementation of the
 * Set interface.  It uses the multiplication method
 * for hashing, and linear probing to resolve collisions.
 * 
 * The complexities of the Set operations are:
 * 		add - amortized constant
 * 		contains - constant
 * 		remove - amortized constant
 * 
 * See the HashST class for an example of a hash table
 * using the division method and separate chaining.
 */
public class HashSet<T> implements Set<T> {

	private T[] ht;			// The hash table itself
	private int numItems;	// The number of entries in use
	private int k;			// log_2 of the size of ht.

	// The magic number is the fractional part of phi
	// multiplied by 2^32.  This is a "very" irrational
	// number, and is therefore good for our purposes.
	//
	//		see https://www.youtube.com/watch?v=sj8Sg8qnjOg
	//
	// for an explanation for what it means for a number
	// to be "very" irrational.
	private final long magic = 2654435769L;

	// It's a good idea to have some minimum size for
	// the hash table.  4 seems about right.  Note
	// that it is easier to calculate the size from
	// the exponent than vice versa, so it's the
	// exponent we set.  Then minSize == 2 << minExp.
	private final int minExp = 2;

	// Compute the hash table index by multiplying the
	// object's hash by our magic number, and taking the
	// top k bits of the low-order 32-bit word.  Since
	// the hash table size is 2^k, the valid indices are
	// in the range [0,2^k), and so ht.length-1 is the
	// bit mask to use to extract those k bits.
	private int hash(T item) {
		long h = item.hashCode() * magic;
		return (int)((h >> (32-k)) & (ht.length - 1));
	}

	// Increment the given hash table index to the
	// next index, wrapping around to zero if
	// necessary.
	private int nextIdx(int idx) {
		idx += 1;
		return idx == ht.length ? 0 : idx;
	}

	// Search the table for the given item.  If it
	// is present, return its index.  If not, return
	// the index of the next null table entry.
	private int search(T item) {
		int idx = hash(item);
		while (ht[idx] != null) {
			if (ht[idx].equals(item))
				return idx;
			idx = nextIdx(idx);
		}
		return idx;
	}

	// Search the table for an empty slot in which
	// to place this item.   This should only be
	// called if it is guaranteed that the item is
	// not already present in the table.
	private int searchNew(T item) {
		int idx = hash(item);
		while (ht[idx] != null) {
			idx = nextIdx(idx);
		}
		return idx;
	}

	// Resize the table using the new value of 'k',
	// and rehash all the table entries.
	@SuppressWarnings("unchecked")
	private void resizeTable() {

		// Save the old table so we can ran through it
		// and rehash all the entries into the new table.
		T[] oldTable = ht;

		// Create the new table.
		T[] ht = (T[]) new Object[1 << k];
		
		// Rehash all the old entries.
		for (T t : oldTable)
		{
			if (t != null) {
				int idx = searchNew(t);
				ht[idx] = t;
			}
		}
	}

	@SuppressWarnings("unchecked")
	public HashSet() {
		k = minExp;
		ht = (T [])new Object[1 << k];
		numItems = 0;
	}
	
	@Override
	public void add(T item) {
		
		// Check to see if the hash table needs to be
		// resized.  Keeping alpha at or below 0.5
		// gives us -- on average -- 1.5 probes per
		// search hit and 2.5 probes per search miss. 
		if ((double)(numItems+1) / ht.length > 0.5) {
			k += 1;
			resizeTable();
		}

		// Search for this item.  If the table entry
		// at the returned index is null, we have a
		// new item to add to the table.  Otherwise,
		// we have a duplicate entry and do nothing.
		int idx = search(item);
		if (ht[idx] == null) {
			ht[idx] = item;
		}
	}

	@Override
	public boolean contains(T item) {
		
		// If search() returns the index of a non-null
		// table, we have an existing item.
		int idx = search(item);
		return ht[idx] != null;
	}

	@Override
	public void remove(T item) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int size() {
		
		// Note that the size is the number of table
		// entries, not the total size of the table.
		return numItems;
	}

	@Override
	public boolean isEmpty() {
		return numItems == 0;
	}

	private class HTIterator implements Iterator<T> {

		int nextIdx;

		private void findNext() {
			while (nextIdx < ht.length && ht[nextIdx] == null)
				nextIdx++;
		}

		public HTIterator() {
			nextIdx = 0;
			findNext();
		}

		@Override
		public boolean hasNext() {
			return nextIdx != ht.length;
		}

		@Override
		public T next() {
			T t = ht[nextIdx++];
			findNext();
			return t;
		}
		
	}

	@Override
	public Iterator<T> iterator() {
		return new HTIterator();
	}
}
