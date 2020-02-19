package edu.normandale.csn;

import java.util.Iterator;

public class LLStack<T> implements Stack<T> {

	LinkedList<T> stk = new LinkedList<>();

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
		T t = stk.removeFront();
		stk.addFront(t);
		return t;
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
}
