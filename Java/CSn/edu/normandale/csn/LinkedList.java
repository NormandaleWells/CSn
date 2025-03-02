package edu.normandale.csn;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

// LinkedList implements a singly-linked list.  Elements
// may be added to the front or back of the list, but
// may only be removed from the front.
//
// This implementation also allows for finding and/or
// removing the minimum and maximum elements.  When
// removing the minimum and maximum elements, it is
// assumed that the list is unordered.  This allows
// the removal functions to be more efficient by
// swapping the min/max element with the front
// item and then calling removeFront().
//
// Because we do not want to impose the requirement
// that the generic type parameter T be a Comparable
// type, functions like minElement() and maxElement()
// that require ordered comparisons need an object
// implementing Comparator<T>.

public class LinkedList<T> implements Iterable<T> {

	// We don't want to require that the class type
	// parameter be Comparable, so for functions that
	// need it, we require the caller of that function
	// to supply a Comparator<T> that can be used to
	// compare items.

	private class Node {
		T data;
		Node next;
		Node(T item, Node n) {
			data = item;
			next = n;
		}
	}

	private Node front;
	private Node back ;
	private int numItems;

	// findMin() assumes that the list is not empty.
	private Node findMin(Comparator<T> comp) {
		Node minNode = front;
		for (Node n = front.next; n != null; n = n.next) {
			if (comp.compare(n.data,  minNode.data) < 0)
				minNode = n;
		}
		return minNode;
	}

	// findMax() assumes that the list is not empty.
	private Node findMax(Comparator<T> comp) {
		Node maxNode = front;
		for (Node n = front.next; n != null; n = n.next) {
			if (comp.compare(n.data,  maxNode.data) > 0)
				maxNode = n;
		}
		return maxNode;
	}

	// Note that Key is not necessarily the same type
	// as our class type parameter T.  All we require
	// is that T.equals() is prepared to accept a
	// value of type Key for comparison.
	// This allows us to, for example, use a linked
	// list of Key/Value pairs, but compare items
	// based on just the Key type.  See LLSymbolTable
	// for an example of how this is used.
	@SuppressWarnings("unlikely-arg-type")
	private <Key> Node findKey(Key key) {
		for (Node n = front; n != null; n = n.next)
			if (n.data.equals(key))
				return n;
		return null;
	}

	// This is for demonstration purposes only.  Real
	// code should use the non-recursive version of
	// the remove() method.
	@SuppressWarnings("unlikely-arg-type")
	private <Key> Node removeRecursive(Node n, Key key) {
		if (n == null)
			return null;
		if (n.data.equals(key)) {
			return n.next;
		}
		n.next = removeRecursive(n.next, key);
		return n;
	}

	// addFront() adds an item to the front of the list.
	public void addFront(T item) {
		front = new Node(item, front);
		if (back == null)
			back = front;
		numItems += 1;
	}

	// addBack() adds an item to the back of the list.
	public void addBack(T item) {
		if (back == null) {
			back = front = new Node(item, null);
		} else {
			Node t = new Node(item, null);
			back.next = t;
			back = t;
		}
		numItems += 1;
	}

	// getFront() returns the item at the front of
	// the list without removing it.
	public T getFront() {
		if (front == null) {
			throw new NoSuchElementException();
		}
		return front.data;
	}

	// getBack() returns the item at the back of
	// the list without removing it.
	public T getBack() {
		if (back == null) {
			throw new NoSuchElementException();
		}
		return back.data;
	}

	// removeFront() removes an item from the front
	// of the list.
	// NOTE: Because of the nature of a linked list,
	// we cannot efficiently remove from the back
	// of the list.
	public T removeFront() {
		if (front == null)
			throw new NoSuchElementException();

		T ret = front.data;
		front.data = null;		// prevent loitering
		front = front.next;
		if (front == null)
			back = null;
		numItems -= 1;
		return ret;
	}
	
	// size() returns the number of items in the list.
	public int size() {
		return numItems;
	}

	// isEmpty() returns true if the list has no items,
	// and false otherwise.
	public boolean isEmpty() {
		return numItems == 0;
	}

	// find() returns the data in the first node that
	// matches the given key.  Note that the key may
	// be a different type from T; doing so will
	// depend on T.equals() being able to handle
	// comparison for equality to Key.
	public <Key> T find(Key key) {
		Node keyNode = findKey(key);
		return keyNode == null ? null : keyNode.data;
	}

	// Since our linked list is unordered, and we
	// have no externally-visible iterators to
	// worry about, we can remove an item by
	// swapping it with the front-most item and
	// calling removeFront().
	public <Key> T remove(Key key) {
		Node keyNode = findKey(key);
		if (keyNode == null)
			return null;

		T temp = keyNode.data;
		keyNode.data = front.data;
		front.data = temp;
		return removeFront();
	}

	// This is for demonstration purposes only.  Real
	// code should use the non-recursive version of
	// the remove() method.
	public <Key> void removeRecursive(Key key) {
		front = removeRecursive(front, key);
	}

	// minElement() returns the minimum element
	// in the list, where "minimum" is defined
	// by the given Comparator<T> object.
	public T minElement(Comparator<T> comp) {
		if (front == null)
			throw new NoSuchElementException();

		return findMin(comp).data;
	}

	// removeMin() returns and removes the minimum
	// element in the list, where "minimum" is
	// defined by the given Comparator<T> object.
	public T removeMin(Comparator<T> comp) {
		if (front == null)
			throw new NoSuchElementException();

		Node minNode = findMin(comp);

		T temp = front.data;
		front.data = minNode.data;
		minNode.data = temp;
		return removeFront();
	}
	
	// maxElement() returns the maximum element
	// in the list, where "maximum" is defined
	// by the given Comparator<T> object.
	public T maxElement(Comparator<T> comp) {
		if (front == null)
			throw new NoSuchElementException();

		return findMax(comp).data;
	}
	
	// removeMax() returns and removes the maximum
	// element in the list, where "maximum" is
	// defined by the given Comparator<T> object.
	public T removeMax(Comparator<T> comp) {
		if (front == null)
			throw new NoSuchElementException();

		Node maxNode = findMax(comp);

		T temp = front.data;
		front.data = maxNode.data;
		maxNode.data = temp;
		return removeFront();
	}

	// ListIterator implements a simple Iterator
	// usable with the Java enhanced for loop.
	private class ListIterator implements Iterator<T> {

		private Node current = front;

		@Override
		public boolean hasNext() {
			return current != null;
		}

		@Override
		public T next() {
			T t = current.data;
			current = current.next;
			return t;
		}
	}

	// iterator() returns an instant of ListIterator.
	@Override
	public Iterator<T> iterator() {
		return new ListIterator();
	}

	// toString() returns a string with the form
	//
	//		n1 -> n2 -> n3 -> null
	//
	// where n1, n2, etc. are the results of
	// calling toString() on the first node,
	// second node, etc.
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (Node n = front; n != null; n = n.next) {
			sb.append(n.data.toString());
			sb.append(" -> ");
		}
		sb.append("null");
		return sb.toString();
	}

	public static void main(String[] args) {
		
		// ad hoc test of removeRecursive().
		LinkedList<String> lls = new LinkedList<>();
		lls.addFront("a");
		lls.addFront("b");
		lls.addFront("c");
		lls.addFront("d");
		System.out.println(lls);
		lls.removeRecursive("b");
		System.out.println(lls);
		lls.removeRecursive("a");
		System.out.println(lls);
		lls.removeRecursive("d");
		System.out.println(lls);
		lls.removeRecursive("c");
		System.out.println(lls);
	}
}
