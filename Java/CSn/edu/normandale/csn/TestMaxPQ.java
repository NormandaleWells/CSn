package edu.normandale.csn;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestMaxPQ {

	private void testOnePQ(MaxPQ<Integer> pq) {
		
		// Simple sanity checks
		
		// Initial state
		assertTrue(pq.isEmpty());
		assertEquals(pq.size(), 0);
		
		// Add/remove a single item.
		pq.insert(5);
		assertFalse(pq.isEmpty());
		assertEquals(pq.size(), 1);
		int maxItem = pq.max();
		assertEquals(maxItem, 5);
		assertFalse(pq.isEmpty());
		assertEquals(pq.size(), 1);
		maxItem = pq.delMax();
		assertEquals(maxItem, 5);
		assertTrue(pq.isEmpty());
		assertEquals(pq.size(), 0);

		// Add/remove several items.
		pq.insert(10);
		assertEquals(pq.max(), (Integer)10);
		pq.insert(15);
		assertEquals(pq.max(), (Integer)15);
		pq.insert(5);
		assertEquals(pq.max(), (Integer)15);
		assertFalse(pq.isEmpty());
		assertEquals(pq.size(), 3);
		assertEquals(pq.delMax(), (Integer)15);
		assertEquals(pq.delMax(), (Integer)10);
		assertEquals(pq.delMax(), (Integer)5);
		assertTrue(pq.isEmpty());
		assertEquals(pq.size(), 0);

		// Add items in increasing order.
		for (int i = 0; i < 40; i++)
			pq.insert(i);
		assertEquals(pq.size(), 40);
		for (int i = 0; i < 40; i++)
			assertEquals(pq.delMax(), (Integer)(39-i));
		assertEquals(pq.size(), 0);

		// Add items in decreasing order.
		for (int i = 0; i < 40; i++)
			pq.insert(39-i);
		assertEquals(pq.size(), 40);
		for (int i = 0; i < 40; i++)
			assertEquals(pq.delMax(), (Integer)(39-i));
		assertEquals(pq.size(), 0);

		// Add items in "random" order.
		pq.insert(100);
		pq.insert(50);
		pq.insert(25);
		pq.insert(150);
		pq.insert(200);
		pq.insert(10);
		pq.insert(15);
		pq.insert(250);
		pq.insert(225);
		assertEquals(pq.size(), 9);
		assertEquals(pq.max(), (Integer)250);
		assertEquals(pq.delMax(), (Integer)250);
		assertEquals(pq.max(), (Integer)225);
		assertEquals(pq.delMax(), (Integer)225);
		assertEquals(pq.max(), (Integer)200);
		assertEquals(pq.delMax(), (Integer)200);
		assertEquals(pq.max(), (Integer)150);
		assertEquals(pq.delMax(), (Integer)150);
		assertEquals(pq.max(), (Integer)100);
		assertEquals(pq.delMax(), (Integer)100);
		assertEquals(pq.max(), (Integer)50);
		assertEquals(pq.delMax(), (Integer)50);
		assertEquals(pq.max(), (Integer)25);
		assertEquals(pq.delMax(), (Integer)25);
		assertEquals(pq.max(), (Integer)15);
		assertEquals(pq.delMax(), (Integer)15);
		assertEquals(pq.max(), (Integer)10);
		assertEquals(pq.delMax(), (Integer)10);
		assertTrue(pq.isEmpty());
		assertEquals(pq.size(), 0);
		
		// Multiple items with the same value.
		pq.insert(1000);
		pq.insert(1000);
		pq.insert(2000);
		assertEquals(pq.size(), 3);
		assertEquals(pq.delMax(), (Integer)2000);
		assertEquals(pq.delMax(), (Integer)1000);
		assertEquals(pq.delMax(), (Integer)1000);
		
		pq.insert(1000);
		pq.insert(1000);
		pq.insert(500);
		assertEquals(pq.size(), 3);
		assertEquals(pq.delMax(), (Integer)1000);
		assertEquals(pq.delMax(), (Integer)1000);
		assertEquals(pq.delMax(), (Integer)500);
		
		pq.insert(2000);
		pq.insert(1000);
		pq.insert(1000);
		assertEquals(pq.size(), 3);
		assertEquals(pq.delMax(), (Integer)2000);
		assertEquals(pq.delMax(), (Integer)1000);
		assertEquals(pq.delMax(), (Integer)1000);
		
		pq.insert(500);
		pq.insert(1000);
		pq.insert(1000);
		assertEquals(pq.size(), 3);
		assertEquals(pq.delMax(), (Integer)1000);
		assertEquals(pq.delMax(), (Integer)1000);
		assertEquals(pq.delMax(), (Integer)500);
	}

	@Test
	public void testLLMaxPQ() {
		MaxPQ<Integer> pq = new LLMaxPQ<Integer>();
		testOnePQ(pq);
	}

	@Test
	public void testRAMaxPQ() {
		MaxPQ<Integer> pq = new RAMaxPQ<Integer>();
		testOnePQ(pq);
	}

	@Test
	public void testHeapMaxPQ() {
		MaxPQ<Integer> pq = new HeapMaxPQ<Integer>(1);
		testOnePQ(pq);
	}

}
