package edu.normandale.csn;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * ArrayQueue implements the Queue interface using a resizeable array. The array
 * is treated in a circular manner; that is, when we reach the end of the array,
 * we wrap around to the beginning. <br>
 * <br>
 * We need track of two indices; the head and tail. Items are added to the tail,
 * and removed from the head. Note that because the indices wrap, we may have
 * tail >= head, or head < tail. So the valid part of the queue is either
 * [head,tail) or [head,q.length)[0,tail).<br>
 * <br>
 * If head == tail, we have an empty queue. Therefore, the queue can really hold
 * only q.length-1 items. Enqueueing an item may temporarily leave tail == head
 * for non-empty queue, but this is fixed before enqueue() returns by resizing
 * the array.
 * 
 * @author rdwells
 *
 * @param <T>
 */
public class ArrayQueue<T> implements Queue<T> {

	T[] queue = null;
	int head;
	int tail;

	@SuppressWarnings("unchecked")
	public ArrayQueue(int initSize) {
		if (initSize == 0)
			initSize = 1;
		queue = (T[]) new Object[initSize];
		head = 0;
		tail = 0;
	}

	public ArrayQueue() {
		this(1);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void enqueue(T item) {

		// Store the new item in the queue.
		queue[tail++] = item;

		// Check to see if we wrapped around.
		if (tail == queue.length)
			tail = 0;

		// If the array is full, reallocate it as twice
		// the current size, and reorganize the queue
		// to start at 0.
		if (tail == head) {
			T[] newQ = (T[]) new Object[queue.length * 2];
			ArrayUtils.move(queue, head, queue.length, newQ, 0);
			ArrayUtils.move(queue, 0, tail, newQ, queue.length - head);
			head = 0;
			tail = queue.length;
			queue = newQ;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public T dequeue() {

		// Make sure the queue isn't empty.
		if (isEmpty())
			throw new NoSuchElementException();

		// Remove the next item.
		T item = queue[head];
		queue[head++] = null;	// avoid loitering

		// Check to see if we wrapped around.
		if (head == queue.length)
			head = 0;

		// We need to make sure we never resize to a
		// zero-length array.
		if (queue.length >= 4 && size() < queue.length / 4) {
			T[] newQ = (T[]) new Object[queue.length / 2];
			if (head <= tail) {
				ArrayUtils.move(queue, head, tail, newQ, 0);
			} else {
				ArrayUtils.move(queue, head, queue.length, newQ, 0);
				ArrayUtils.move(queue, 0, tail, newQ, queue.length - head);
			}
			tail = size();
			head = 0;
			queue = newQ;
		}

		return item;
	}

	@Override
	public boolean isEmpty() {
		return tail == head;
	}

	@Override
	public int size() {
		// Note that enqueue() is written so that if head == tail,
		// we must have an empty queue.
		return head <= tail ? tail - head : queue.length - (head - tail);
	}

	public class ArrayIterator implements Iterator<T> {
		int current = head;
		int last = tail;

		@Override
		public boolean hasNext() {
			return current != last;
		}

		@Override
		public T next() {
			if (current == last)
				throw new NoSuchElementException();
			T item = queue[current++];
			if (current == queue.length)
				current = 0;
			return item;
		}
	}

	@Override
	public Iterator<T> iterator() {
		return new ArrayIterator();
	}

	public static void main(String[] args) {
		Queue<String> queue = new ArrayQueue<String>(1);
		InteractiveCollectionTests.testQueue(queue);
	}
}
