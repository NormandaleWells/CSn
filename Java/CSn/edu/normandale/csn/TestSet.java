package edu.normandale.csn;

import java.util.Arrays;
import static org.junit.Assert.*;
import org.junit.Test;

public class TestSet {

	private void testBag(Set<String> b) {
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
	public void testUnorderedArraySet() {
		Set<String> b = new UnorderedArraySet<>();
		testBag(b);
	}

	@Test
	public void testLinkedSet() {
		Set<String> b = new LinkedSet<>();
		testBag(b);
	}
}
