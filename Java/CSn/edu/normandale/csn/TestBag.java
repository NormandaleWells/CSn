package edu.normandale.csn;

import java.util.Arrays;
import static org.junit.Assert.*;
import org.junit.Test;

public class TestBag {

	private void testBag(Bag<String> b) {
		assertTrue(b.isEmpty());
		assertEquals(b.size(), 0);

		b.add("A");
		assertFalse(b.isEmpty());
		assertEquals(b.size(), 1);
		
		b.add("B");
		b.add("C");
		assertFalse(b.isEmpty());
		assertEquals(b.size(), 3);
		
		String[] expected = { "A", "B", "C" };
		String[] items = TestUtils.iterToArray(b, b.size());
		Arrays.sort(items);
		org.junit.Assert.assertArrayEquals(expected, items);
	}

	@Test
	public void testRABag() {
		Bag<String> b = new RABag<String>();
		testBag(b);
	}

	@Test
	public void testLLBag() {
		Bag<String> b = new LLBag<String>();
		testBag(b);
	}
}
