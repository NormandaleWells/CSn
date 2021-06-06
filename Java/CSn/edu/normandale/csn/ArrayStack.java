package edu.normandale.csn;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayStack<T> implements Stack<T> {

	T[] stack;
	int top = 0;

	@SuppressWarnings("unchecked")
	public ArrayStack(int initSize) {
		if (initSize == 0)
			initSize = 1;
		stack = (T[]) new Object[initSize];
	}

	public ArrayStack() {
		this(1);
	}

	@SuppressWarnings("unchecked")
	private void resize(int newSize) {
		T[] newStack = (T[]) new Object[newSize];
		ArrayUtils.move(stack, newStack, 0, top);
		stack = newStack;
	}

	@Override
	public void push(T item) {

		// Make sure there's room. If there isn't,
		// resize the array to make it twice as big.
		if (top == stack.length) {
			resize(top * 2);
		}

		// Add the new item.
		stack[top++] = item;
	}

	@Override
	public T pop() {

		// Make sure the stack wasn't empty.
		if (isEmpty())
			throw new NoSuchElementException();

		// Get the top item.
		T item = stack[--top];
		stack[top] = null;		// avoid loitering

		// See if this reduced the stack size to where we can
		// reallocate it.
		if (top < stack.length / 4) {
			resize(stack.length / 2);
		}

		// Return the top item.
		return item;
	}

	@Override
	public T peek() {
		if (isEmpty())
			throw new NoSuchElementException();
		return stack[top-1];
	}

	@Override
	public boolean isEmpty() {
		return top == 0;
	}

	@Override
	public int size() {
		return top;
	}

	public class StackIterator implements Iterator<T> {
		int t = top;	// invariant: t is the number of items left to see

		@Override
		public boolean hasNext() {
			return t != 0;
		}

		@Override
		public T next() {
			if (t == 0)
				throw new NoSuchElementException();
			return stack[--t];
		}
	}

	@Override
	public Iterator<T> iterator() {
		return new StackIterator();
	}

	public static void main(String[] args) {
		ArrayStack<String> stack = new ArrayStack<>(1);
		TestCollectionUtilities.testStack(stack);
	}
}
