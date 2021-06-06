package edu.normandale.csn;

import static org.junit.Assert.*;
import org.junit.Test;

public class TestStack {

	private void testStack(Stack<String> stk) {

		assertTrue(stk.isEmpty());
		assertEquals(stk.size(), 0);

		stk.push("A");
		assertFalse(stk.isEmpty());
		assertEquals(stk.size(), 1);
		assertEquals(stk.peek(), "A");

		assertEquals(stk.pop(), "A");
		assertTrue(stk.isEmpty());
		assertEquals(stk.size(), 0);
		
		stk.push("B");
		assertEquals(stk.peek(), "B");
		stk.push("C");
		assertEquals(stk.peek(), "C");
		assertFalse(stk.isEmpty());
		assertEquals(stk.size(), 2);
		
		assertEquals(stk.pop(), "C");
		assertFalse(stk.isEmpty());
		assertEquals(stk.size(), 1);
		
		assertEquals(stk.pop(), "B");
		assertTrue(stk.isEmpty());
		assertEquals(stk.size(), 0);

		stk.push("D");
		stk.push("E");
		stk.push("F");
		stk.push(stk.pop());
		assertEquals(stk.peek(), "F");
		stk.push("G");
		assertEquals(stk.size(), 4);
		
		String[] expected = { "G", "F", "E", "D" };
		String[] items = TestUtils.iterToArray(stk, stk.size());
		org.junit.Assert.assertArrayEquals(expected, items);
	}

	@Test
	public void testRAStack() {
		ArrayStack<String> b = new ArrayStack<>();
		testStack(b);
	}

	@Test
	public void testLLStack() {
		LinkedStack<String> b = new LinkedStack<>();
		testStack(b);
	}
}
