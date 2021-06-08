package edu.normandale.csn;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestQueue {
	private void testQueue(Queue<String> q) {

		assertTrue(q.isEmpty());
		assertEquals(q.size(), 0);

		q.enqueue("A");
		assertFalse(q.isEmpty());
		assertEquals(q.size(), 1);

		assertEquals(q.dequeue(), "A");
		assertTrue(q.isEmpty());
		assertEquals(q.size(), 0);
		
		q.enqueue("B");
		q.enqueue("C");
		assertFalse(q.isEmpty());
		assertEquals(q.size(), 2);
		
		assertEquals(q.dequeue(), "B");
		assertFalse(q.isEmpty());
		assertEquals(q.size(), 1);
		
		assertEquals(q.dequeue(), "C");
		assertTrue(q.isEmpty());
		assertEquals(q.size(), 0);

		q.enqueue("D");
		q.enqueue("E");
		q.enqueue("F");
		q.enqueue(q.dequeue());
		q.enqueue("G");
		assertEquals(q.size(), 4);
		
		String[] expected = { "E", "F", "D", "G" };
		String[] items = TestUtils.iterToArray(q, q.size());
		org.junit.Assert.assertArrayEquals(expected, items);
	}

	@Test
	public void testRAQueue() {
		Queue<String> b = new ArrayQueue<>();
		testQueue(b);
	}

	@Test
	public void testLLQueue() {
		Queue<String> b = new LinkedQueue<>();
		testQueue(b);
	}
}
