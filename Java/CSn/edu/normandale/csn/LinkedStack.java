package edu.normandale.csn;

import java.util.Iterator;

public class LinkedStack<T> implements Stack<T> {

	LinkedList<T> stk = new LinkedList<T>();

	@Override
	public void push(T item) {
		stk.addFront(item);
	}

	@Override
	public T pop() {
		return stk.removeFront();
	}

	@Override
	public T peek() {
		return stk.getFront();
	}

	@Override
	public boolean isEmpty() {
		return stk.isEmpty();
	}

	@Override
	public int size() {
		return stk.size();
	}

	@Override
	public Iterator<T> iterator() {
		return stk.iterator();
	}

	public static void main(String[] args) {
		Stack<String> queue = new LinkedStack<String>();
		TestCollectionUtilities.testStack(queue);
	}
}
