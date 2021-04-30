package edu.normandale.csn;

//import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;

// The code is a combination of 3 sources:
//		_Algorithms, 4th Edition_, by Sedgewick and Wayne
//		https://www.coursera.org/learn/data-structures/home/welcome
//		_Data Structures Essentials_, by Lysecky and Vahid (zyBooks)
//
// The influence of Sedgewick and Wayne is most apparent in
// the put() method.  I took their basic idea having the
// internal function return the new subtree root, and applied
// it to AVL trees.
//
// The influence of Lysecky and Vahid is most apparent in
// the AVL rebalancing after insertion.  The main difference
// is that this code assigns a height of 0 to a null tree,
// and a height of 1 to a leaf (L&V use -1 and 0).
//
// Much of the code involving deletion and iteration
// is derived from the Coursera course noted above.  The main
// changes from the course code to this code are:
//
//		1. I changed from recursive solutions to iterative ones.
//		2. It uses a stack rather than parent links.
//
// I believe the latter change is unique with me, though I
// can't believe I'm the first to think of it.

public class AVLTreeMap<Key extends Comparable<Key>, Value> implements OrderedMap<Key, Value> {

	private class Node {
		Key key;
		Value value;
		Node left;
		Node right;
		int size = 1;
		int height = 1;

		Node(Key k, Value v) {
			this.key = k;
			this.value = v;
		}
	}
	
	Node root = null;

	// Return the size of the given node.  This takes
	// into account the possibility that n is null.
	private int size(Node n) {
		return n == null ? 0 : n.size;
	}

	// Return the height of the given node.  This takes
	// into account the possibility that n is null.
	private int height(Node n) {
		return n == null ? 0 : n.height;
	}

	// Recompute the size of the given node, and store
	// it in the Node.
	private void recomputeSize(Node n) {
		n.size = size(n.left) + size(n.right) + 1;
	}

	// Recompute the height of the given node, and store
	// it in the Node.
	private void recomputeHeight(Node n) {
		n.height = Math.max(height(n.left), height(n.right)) + 1;
	}

	// Calculate the balance of the given node.  The balance
	// is the signed difference between the left node height
	// and the right node height.
	private int calcBalance(Node n) {
		return height(n.left) - height(n.right);
	}

	// rotateRight - rotate a subtree to the right.
	// Returns the new root node of the subtree.
	//    Before:      After:
	//      n            l
	//     / \          / \
	//    l   t3       t1  n
	//   / \              / \
	//  t1 t2            t2 t3
	private Node rotateRight(Node n) {
		Node l = n.left;
		n.left = l.right;
		l.right = n;
		recomputeSize(n);
		recomputeSize(l);
		recomputeHeight(n);
		recomputeHeight(l);
		return l;
	}
	
	// rotateLeft - rotate a subtree to the left.
	// Returns the new root node of the subtree.
	//   Before:      After:
	//     n            r
	//    / \          / \
	//   t1  r       n    t3
	//      / \     / \
	//     t2 t3   t1 t2
	private Node rotateLeft(Node n) {
		Node r = n.right;
		n.right= r.left;
		r.left = n;
		recomputeSize(n);
		recomputeSize(r);
		recomputeHeight(n);
		recomputeHeight(r);
		return r;
	}
	
	// This internal version of put() inserts the given
	// key/value pair into the subtree whose root is n.
	// It returns the new root of that subtree.
	// The subtree root changes in two cases:
	//     (1) n is null, and so the new subtree
	//         is a new node
	//     (2) insertion into the subtree required
	//         a rebalancing operation at n
	// Otherwise, the returned subtree root is
	// simply n itself.
	private Node put(Node n, Key k, Value v) {

		if (n == null)
			return new Node(k, v);

		// The key to understanding this code is to realize
		// that the assignments are generally no-ops; that
		// is, we're almost always assigning 'left' or
		// 'right' with its current value.  The only time
		// this doesn't happen is when we create a new
		// node or rebalance the tree.
		//
		// I first saw this technique used in the algs4
		// library supplied with Sedgewick and Wayne's
		// _Algorithms, 4th edition_.  In correspondence
		// with Dr. Sedgewick he said this was original
		// with him.  I simply adapted the technique to
		// work with AVL trees.
		int c = k.compareTo(n.key);
		if      (c < 0) n.left  = put(n.left,  k, v);
		else if (c > 0) n.right = put(n.right, k, v);
		else n.value = v;
		
		// See if the subtree rooted at n needs to be
		// rebalanced.
		int bal = calcBalance(n);
		if (bal > 1) {
			// The left tree is larger.  If the right
			// subtree of n.left is larger, we have
			// to do a double rotation.
			if (calcBalance(n.left) < 0)
				n.left = rotateLeft(n.left);
			// Now a single right rotation will
			// fix the tree.
			n = rotateRight(n);
		}
		else if (bal < -1) {
			// The right tree is larger.  If the left
			// subtree of n.right is larger, we have
			// to do a double rotation.
			if (calcBalance(n.right) > 0)
				n.right = rotateRight(n.right);
			// Now a single left rotation will
			// fix the tree.
			n = rotateLeft(n);
		}
		else {
			// No rebalance need.  Just recompute
			// our internal node information.
			recomputeSize(n);
			recomputeHeight(n);
		}

		return n;
	}

	// Return the minimum node of the sub-tree rooted
	// at Node n.  This may be n itself.
	private Node leftDescendant(Node n, Stack<Node> stk) {
		while (n.left != null) {
			stk.push(n);
			n = n.left;
		}
		return n;
	}
	
	// Moves back up the tree looking for a parent
	// greater than the given node.  Note that unlike
	// leftDescendant(), this method will never return
	// n itself.
	private Node rightAncestor(Node n, Stack<Node> stk) {

		// Unfortunately, I could not find a way
		// to avoid the two isEmpty() / pop()
		// sequences.
		if (stk.isEmpty())
			return null;
		Node parent = stk.pop();

		// The comparison is true if we the parent-key
		// link was a right link.
		while (n.key.compareTo(parent.key) > 0) {
			if (stk.isEmpty())
				return null;
			n = parent;
			parent = stk.pop();			
		}

		return parent;
	}

	// next() finds the next node after the given one,
	// returning null if the given node is the last one.
	// The Stack object contains the ancestor nodes of
	// Node n.
	private Node next(Node n, Stack<Node> stk) {
		if (n.right != null) {
			stk.push(n);
			return leftDescendant(n.right, stk);
		}
		else
			return rightAncestor(n, stk);
	}

	// WARNING: This does Hibbard deletion, which is known to skew
	// the tree with large numbers of deletions.  This can be
	// avoided by randomly picking between replacing the deleted
	// node with its predecessor rather than the successor.
	private Node delete(Node n, Key k) {
		
		// If this node does not have the key,
		// search down the left or right tree.
		int c = n.key.compareTo(k);
		if (c < 0)
			n.right = delete(n.right, k);
		else if (c > 0)
			n.left  = delete(n.left,  k);
		
		// At this point we know that node 'n'
		// is the one to delete.  If either
		// child is null, just promote the
		// other child.  Note that this also
		// handles the case where both children
		// are null.  Note that in these cases,
		// there is no need to fix up n.size,
		// since node 'n' is going away.
		else if (n.right == null)
			return n.left;
		else if (n.left == null)
			return n.right;
		
		// Okay, we have the non-trivial case.
		else {
			Stack<Node> stk = new ArrayStack<Node>();
			Node x = next(n, stk);
			// Since we know what n.right != null,
			// we know that next() cannot return
			// null.
			// Since node x is the next greater
			// node after n, we can just move the
			// key and value from x into n.  This
			// effectively deletes node n.
			n.key = x.key;
			n.value = x.value;
			// Now we need to unlink node x.
			// Since we know that x.left is null,
			// we can simply promote x.right to
			// the parent, which is at the top
			// of the stack.
			Node p = stk.pop();
			p.left = x.right;
			
			// We can probably fix up sizes by
			// subtracting one from the size of
			// every node on the stack, but
			// doing it this way may set us up
			// better for when we have to
			// rebalance the tree.
			recomputeSize(p);
			while (!stk.isEmpty()) {
				recomputeSize(stk.pop());
			}
			
			// Due to how next() works, node 'n'
			// was on the stack, so its size has
			// be recomputed.  We can simply
			// return.
			return n;
		}
		
		// If we did delete something, we need to
		// fix up the size.
		recomputeSize(n);
		return n;
	}

	private class TreeIterator implements Iterator<Key> {

		ArrayStack<Node> stk = new ArrayStack<Node>();
		Node next;

		public TreeIterator() {
			next = root == null ? null : leftDescendant(root, stk);
		}

		@Override
		public boolean hasNext() {
			return next != null;
		}

		@Override
		public Key next() {
			if (next == null)
				throw new NoSuchElementException();
			Key k = next.key;
			next = AVLTreeMap.this.next(next, stk);
			return k;
		}
	}

	private class TreeIterable implements Iterable<Key> {

		@Override
		public Iterator<Key> iterator() {
			return new TreeIterator();
		}
		
	}

	///////////////////////////////////
	//
	// Standard set of symbol table methods.
	//

	@Override
	public void put(Key k, Value v) {
		root = put(root, k, v);
	}

	@Override
	public Value get(Key k) {
		Node n = root;
		while (n != null) {
			int c = k.compareTo(n.key);
			if      (c < 0) n = n.left;
			else if (c > 0) n = n.right;
			else return n.value;
		}
		return null;
	}

	@Override
	public boolean contains(Key k) {
		return get(k) != null;
	}

	@Override
	public void delete(Key k) {
		root = delete(root, k);
	}

	@Override
	public int size() {
		if (root == null)
			return 0;
		return root.size;
	}

	@Override
	public boolean isEmpty() {
		return root == null;
	}

	@Override
	public Iterable<Key> keys() {
		return new TreeIterable();
	}

	////////////////////////////////////
	//
	// Methods from Object
	//

	private void toStringHelper(Node n, StringBuilder sb) {
		if (n == null) {
			sb.append("\u2205");
		} else {
			sb.append("([");
			sb.append(n.key.toString());
			sb.append(',');
			sb.append(n.value.toString());
			sb.append(',');
			sb.append(n.size);
			sb.append(',');
			sb.append(n.height);
			sb.append(']');
			sb.append(' ');
			toStringHelper(n.left, sb);
			sb.append(' ');
			toStringHelper(n.right, sb);
			sb.append(")");
		}
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		toStringHelper(root, sb);

		return sb.toString();
	}

	///////////////////////////////////
	//
	// Additional methods for ordered symbol tables.
	//

	@Override
	public Key min() {
		if (root == null)
			return null;
		Node n = root;
		while (n.left != null)
			n = n.left;
		return n.key;
	}

	@Override
	public Key max() {
		if (root == null)
			return null;
		Node n = root;
		while (n.right!= null)
			n = n.right;
		return n.key;
	}

	@Override
	public Key lowerBound(Key k) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Key upperBound(Key k) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<Key> keys(Key lo, Key hi) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public static void main(String[] args) throws FileNotFoundException {

//		FileInputStream f = new FileInputStream(args[0]);
		Scanner scnr = new Scanner(System.in);

		AVLTreeMap<String, Integer> st = new AVLTreeMap<>();

		int count = 0;
		while (scnr.hasNext()) {
			String s = scnr.next();
			st.put(s, count++);
			System.out.println(st);
		}
		
		scnr.close();
		
		System.out.println(st);
		
		for (String s : st.keys()) {
			System.out.printf("[%s,%s]\n", s, st.get(s));
		}
	}
}
