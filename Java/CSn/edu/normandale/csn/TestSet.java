package edu.normandale.csn;

import java.util.Arrays;
import static org.junit.Assert.*;
import org.junit.Test;

public class TestSet {

	private void testSet(Set<String> set) {
		assertTrue(set.isEmpty());
		assertEquals(set.size(), 0);

		set.add("A");
		assertFalse(set.isEmpty());
		assertEquals(set.size(), 1);
	
		set.add("B");
		set.add("C");
		assertFalse(set.isEmpty());
		assertEquals(set.size(), 3);
		
		set.add("C");
		set.add("A");
		set.add("B");
		assertFalse(set.isEmpty());
		assertEquals(set.size(), 3);

		String[] expected = { "A", "B", "C" };
		String[] items = TestUtils.iterToArray(set, set.size());
		Arrays.sort(items);
		assertArrayEquals(expected, items);

		set.add("D");
		set.add("E");
		set.add("F");
		assertFalse(set.isEmpty());
		assertEquals(set.size(), 6);

		set.remove("D");
		set.remove("A");
		set.remove("F");
		assertFalse(set.isEmpty());
		assertEquals(set.size(), 3);

		set.remove("F");
		set.remove("A");
		assertFalse(set.isEmpty());
		assertEquals(set.size(), 3);

		String[] expected2 = { "B", "C", "E" };
		items = TestUtils.iterToArray(set, set.size());
		Arrays.sort(items);
		assertArrayEquals(expected2, items);

		set.remove("E");
		set.remove("B");
		set.remove("C");
		assertTrue(set.isEmpty());
		assertEquals(set.size(), 0);

		set.remove("G");
		assertTrue(set.isEmpty());
		assertEquals(set.size(), 0);

		set.add("G");
		set.add("H");
		assertFalse(set.isEmpty());
		assertEquals(set.size(), 2);
	}

	@Test
	public void testUnorderedArraySet() {
		Set<String> b = new UnorderedArraySet<>();
		testSet(b);
	}

	@Test
	public void testLinkedSet() {
		Set<String> b = new LinkedSet<>();
		testSet(b);
	}

	@Test
	public void testOrderedArraySet() {
		Set<String> b = new OrderedArraySet<>();
		testSet(b);
	}

	@Test
	public void testBSTSet() {
		Set<String> b = new BSTSet<>();
		testSet(b);
	}
}
