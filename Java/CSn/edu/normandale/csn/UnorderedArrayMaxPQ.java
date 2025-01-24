package edu.normandale.csn;

import java.util.Iterator;

public class UnorderedArrayMaxPQ <T extends Comparable<T>> implements MaxPQ<T>, Iterable<T> {

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
		q[count++] = value;
    }

    @Override
    public T max() {
		if (count == 0)
			throw new RuntimeException("UnorderedArrayMaxPQ underflow");
        int idx = ArrayUtils.maxElement(q, 0, count);
		return q[idx];
    }

    @Override
    public T delMax() {
		if (count == 0)
			throw new RuntimeException("OrderedArrayMaxPQ underflow");

        // Since the array is unordered, there's no need to remove the max
        // from the middle and shift the elements.  Instead, we just exchange
        // the max with the last item, and remove it from the end.
        int idx = ArrayUtils.maxElement(q, 0, count);
        ArrayUtils.swap(q, idx, count-1);
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
        @SuppressWarnings("unchecked")
        T[] qCopy = (T[]) new Comparable[count];
        Sort.quicksort(qCopy);
        ArrayUtils.copy(q, qCopy, 0, count);
            return ArrayUtils.getBackwardIterator(q, 0, count);
    }    
}
