package edu.normandale.csn;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestSymbolTable {

	private void testOrderedST(OrderedMap<String,Integer> st) {
		
		assertEquals(st.min(), null);
		assertEquals(st.max(), null);

		st.put("C", 3);
		assertEquals(st.min(), "C");
		assertEquals(st.max(), "C");

		st.put("B", 2);
		st.put("D", 4);
		assertEquals(st.min(), "B");
		assertEquals(st.max(), "D");

		st.put("A", 1);
		st.put("E", 5);
		assertEquals(st.min(), "A");
		assertEquals(st.max(), "E");
	}

	private void testSymbolTable(Map<String,Integer> st) {
		
		assertTrue(st.isEmpty());
		assertEquals(st.size(), 0);

		st.put("C", 3);
		assertFalse(st.isEmpty());
		assertEquals(st.size(), 1);
		assertTrue(st.contains("C"));
		assertEquals(st.get("C"), (Integer)3);

		st.put("B", 2);
		assertTrue(st.contains("B"));
		assertEquals(st.get("B"), (Integer)2);
		assertEquals(st.size(), 2);
		assertTrue(st.contains("C"));
		assertEquals(st.get("C"), (Integer)3);

		st.put("D", 4);
		assertTrue(st.contains("D"));
		assertEquals(st.get("D"), (Integer)4);
		assertEquals(st.size(), 3);
		assertTrue(st.contains("B"));
		assertEquals(st.get("B"), (Integer)2);
		assertTrue(st.contains("C"));
		assertEquals(st.get("C"), (Integer)3);

		st.put("A", 1);
		st.put("E", 5);
		assertEquals(st.size(), 5);
	}

	@Test
	public void testLLSymbolTable() {
		LinkedMap<String,Integer> llst = new LinkedMap<String,Integer>();
		testSymbolTable(llst);
	}

	@Test
	public void testOrderedArrayST() {
		OrderedArrayMap<String,Integer> oast = new OrderedArrayMap<String,Integer>();
		testSymbolTable(oast);

		oast = new OrderedArrayMap<String,Integer>();
		testOrderedST(oast);
	}

	@Test
	public void testUnorderedArrayST() {
		UnorderedArrayMap<String,Integer> uast = new UnorderedArrayMap<String,Integer>();
		testSymbolTable(uast);
	}

	@Test
	public void testBinaryTreeST() {
		AVLTreeMap<String,Integer> btst = new AVLTreeMap<String,Integer>();
		testSymbolTable(btst);

		btst = new AVLTreeMap<String,Integer>();
		testOrderedST(btst);
	}
}
