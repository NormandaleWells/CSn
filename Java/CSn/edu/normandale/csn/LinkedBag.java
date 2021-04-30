package edu.normandale.csn;

import java.util.Iterator;

public class LinkedBag<T> implements Bag<T> {

	private class Node {
		T item;
		Node next;

		Node(T i, Node n) {
			item = i;
			next = n;
		}
	}

	private Node first = null;
	private int count = 0;

	@Override
	public void add(T item) {
		first = new Node(item, first);
		count++;
	}

	@Override
	public boolean isEmpty() {
		return first == null;
	}

	@Override
	public int size() {
		return count;
	}

	private class BagIterator implements Iterator<T>
	{
		private Node current = first;

		@Override
		public boolean hasNext() {
			return current != null;
		}

		@Override
		public T next() {
			T item = current.item;
			current = current.next;
			return item;
		}
		
	}

	@Override
	public Iterator<T> iterator() {
		return new BagIterator();
	}
	
	public static void main(String[] args) {
		Bag<String> bag = new LinkedBag<String>();
		TestCollectionUtilities.testBag(bag);
	}
}
