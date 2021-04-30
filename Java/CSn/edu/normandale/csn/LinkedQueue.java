package edu.normandale.csn;

import java.util.Iterator;

public class LLQueue<T> implements Queue<T> {

	LinkedList<T> q = new LinkedList<>();

	@Override
	public void enqueue(T item) {
		q.addBack(item);
	}

	@Override
	public T dequeue() {
		return q.removeFront();
	}

	@Override
	public boolean isEmpty() {
		return q.isEmpty();
	}

	@Override
	public int size() {
		return q.size();
	}

	@Override
	public Iterator<T> iterator() {
		return q.iterator();
	}

	public static void main(String[] args) {
		Queue<String> queue = new LLQueue<String>();
		TestCollectionUtilities.testQueue(queue);
	}
}
