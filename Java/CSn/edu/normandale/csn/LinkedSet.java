package edu.normandale.csn;

import java.util.Iterator;

public class LinkedSet<T> implements Set<T> {

	private class Node {
		T item;
		Node next;

		Node(T i, Node n) {
			item = i;
			next = n;
		}
	}

	private Node first = null;
	private int numItems = 0;

	@Override
	public void add(T item) {
		if (contains(item)) return;
		first = new Node(item, first);
		numItems++;
	}

	@Override
	public boolean contains(T item) {
		for (Node n = first; n != null; n = n.next) {
			if (n.item.equals(item)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void remove(T item) {
		for (Node n = first; n != null; n = n.next) {
			if (n.item.equals(item)) {
				// Since the list is unordered, rather
				// than try to remove this node, we'll
				// just copy the first item to this
				// node, and unlink the first node.
				Node oldFirst = first;
				n.item = first.item;
				first = first.next;
				oldFirst.item = null;
				oldFirst.next = null;
				numItems -= 1;
			}
		}
	}

	@Override
	public boolean isEmpty() {
		return first == null;
	}

	@Override
	public int size() {
		return numItems;
	}

	private class LinkedSetIterator implements Iterator<T>
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
		return new LinkedSetIterator();
	}
	
	public static void main(String[] args) {
		Set<String> bag = new LinkedSet<String>();
		InteractiveCollectionTests.testSet(bag);
	}
}
