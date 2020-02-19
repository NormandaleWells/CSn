package edu.normandale.csn;

import java.util.Iterator;

public class LinkedIntList implements Iterable<Integer> {

	private class Node {
		int data;
		Node next;
		Node(int item, Node n) {
			data = item;
			next = n;
		}
	}

	private Node front;
	private Node back;
	private int numItems;

	public LinkedIntList() {
		front = null;
		back = null;
		numItems = 0;
	}

	public void addFront(int item) {
		front = new Node(item, front);
		if (back == null)
			back = front;
		numItems += 1;
	}

	public void addBack(int item) {
		if (back == null) {
			back = front = new Node(item, null);
		} else {
			Node t = new Node(item, null);
			back.next = t;
			back = t;
		}
		numItems += 1;
	}

	public int removeFront() {
		int ret = front.data;
		front = front.next;
		if (front == null)
			back = null;
		numItems -= 1;
		return ret;
	}
	
	public int size() {
		return numItems;
	}

	public boolean isEmpty() {
		return numItems == 0;
	}

	public int peek() {
		return front.data;
	}
	
	public void exchange() {
		int t = front.data;
		front.data = front.next.data;
		front.next.data = t;
	}

	public int minElement() {
		int minItem = front.data;
		for (Node n = front.next; n != null; n = n.next) {
			if (n.data < minItem)
				minItem = n.data;
		}
		return minItem;
	}
	
	public int removeMin() {
		Node minNode = front;
		for (Node n = front.next; n != null; n = n.next) {
			if (n.data < minNode.data)
				minNode = n;
		}
		int temp = front.data;
		front.data = minNode.data;
		minNode.data = temp;
		return removeFront();
	}
	
	public int maxElement() {
		int maxItem = front.data;
		for (Node n = front.next; n != null; n = n.next) {
			if (n.data > maxItem)
				maxItem = n.data;
		}
		return maxItem;
	}
	
	public int removeMax() {
		Node maxNode = front;
		for (Node n = front.next; n != null; n = n.next) {
			if (n.data > maxNode.data)
				maxNode = n;
		}
		int temp = front.data;
		front.data = maxNode.data;
		maxNode.data = temp;
		return removeFront();
	}

	private class ListIterator implements Iterator<Integer> {

		private Node current = front;

		@Override
		public boolean hasNext() {
			return current != null;
		}

		@Override
		public Integer next() {
			Integer t = current.data;
			current = current.next;
			return t;
		}
	}
	@Override
	public Iterator<Integer> iterator() {
		return new ListIterator();
	}
	
	public static void main(String[] args) {
		
		int i = 0;
		assert(i++ == 0);
		if (i != 1) {
			System.out.println("Not running with assertions turned on!");
			System.out.println("Use the -ea option to enable assertions.");
		}

		// Create a new list make sure it's empty.
		LinkedIntList ll = new LinkedIntList();
		assert(ll.isEmpty());
		assert(ll.size() == 0);
		
		// Add an item, and make sure that
		//	(1) the list is not empty
		//	(2) the list has one item
		ll.addFront(1);
		assert(!ll.isEmpty());
		assert(ll.size() == 1);
		
		// Remove an item and make sure that
		//	(1) the list is empty
		//	(2) the list has zero items
		//	(3) we got back the value we pushed
		int val1 = ll.removeFront();
		assert(ll.isEmpty());
		assert(ll.size() == 0);
		assert(val1 == 1);
		
		// Add and remove two items.
		ll.addFront(2);
		assert(!ll.isEmpty());
		assert(ll.size() == 1);
		ll.addFront(3);
		assert(!ll.isEmpty());
		assert(ll.size() == 2);
		int val3 = ll.removeFront();
		int val2 = ll.removeFront();
		assert(ll.isEmpty());
		assert(ll.size() == 0);
		assert(val3 == 3);
		assert(val2 == 2);
		
		// Add to the back.
		ll.addBack(4);
		assert(!ll.isEmpty());
		assert(ll.size() == 1);
		int val4 = ll.removeFront();
		assert(ll.isEmpty());
		assert(ll.size() == 0);
		assert(val4 == 4);
		
		// Test addBack then addFront
		ll.addBack(5);
		ll.addFront(6);
		int val6 = ll.removeFront();
		int val5 = ll.removeFront();
		assert(ll.isEmpty());
		assert(ll.size() == 0);
		assert(val5 == 5);
		assert(val6 == 6);
		
		// Test addFront then addBack
		ll.addFront(7);
		ll.addBack(8);
		int val7 = ll.removeFront();
		int val8 = ll.removeFront();
		assert(ll.isEmpty());
		assert(ll.size() == 0);
		assert(val7 == 7);
		assert(val8 == 8);
		
		// Test peek
		ll.addFront(9);
		int val9 = ll.peek();
		assert(val9 == 9);
		assert(!ll.isEmpty());
		assert(ll.size() == 1);
		ll.addBack(10);
		val9 = ll.peek();
		assert(val9 == 9);
		assert(!ll.isEmpty());
		assert(ll.size() == 2);
		ll.removeFront();
		int val10 = ll.peek();
		assert(val10 == 10);
		val10 = ll.removeFront();
		assert(val10 == 10);
		assert(ll.isEmpty());
		assert(ll.size() == 0);
		
		// Test exchange
		ll.addFront(11);
		ll.addFront(12);
		assert(ll.peek() == 12);
		ll.exchange();
		assert(ll.peek() == 11);
		ll.addFront(13);
		assert(ll.peek() == 13);
		ll.exchange();
		assert(ll.peek() == 11);
		int val11 = ll.removeFront();
		assert(val11 == 11);
		assert(ll.peek() == 13);
		ll.exchange();
		assert(ll.peek() == 12);
		int val12 = ll.removeFront();
		assert(val12 == 12);
		int val13 = ll.removeFront();
		assert(val13 == 13);
		assert(ll.isEmpty());
		assert(ll.size() == 0);
		
		// Test minElement and maxElement
		LinkedIntList ll2 = new LinkedIntList();
		ll2.addFront(50);
		assert(ll2.minElement() == 50);
		assert(ll2.maxElement() == 50);
		ll2.addFront(100);
		assert(ll2.minElement() == 50);
		assert(ll2.maxElement() == 100);
		ll2.addBack(ll2.removeFront());
		assert(ll2.minElement() == 50);
		assert(ll2.maxElement() == 100);

		LinkedIntList ll3 = new LinkedIntList();
		ll3.addFront(0);
		ll3.addFront(Integer.MAX_VALUE);
		ll3.addFront(0);
		ll3.addFront(Integer.MIN_VALUE);
		ll3.addFront(0);
		assert(ll3.minElement() == Integer.MIN_VALUE);
		assert(ll3.maxElement() == Integer.MAX_VALUE);
	}
}
