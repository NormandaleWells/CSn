package edu.normandale.csn;

import java.lang.Math;
import java.util.ArrayList;
import java.util.Iterator;

public class BSTSet<T extends Comparable<T>> implements OrderedSet<T> {

    private class Node {
        T data;
        int height = 1;
        int size = 1;
        Node left = null;
        Node right = null;

        Node(T data) {
            this.data = data;
        }
    }

    // Invariant: root == null <-> numItems == 0
    private Node root = null;
    private int numItems = 0;

    private int size(Node n) {
        return n == null ? 0 : n.size;
    }

    private int height(Node n) {
        return n == null ? 0 : n.height;
    }

	private void recomputeSize(Node n) {
		n.size = size(n.left) + size(n.right) + 1;
	}

	private void recomputeHeight(Node n) {
		n.height = Math.max(height(n.left), height(n.right)) + 1;
	}

    private Node add(Node n, T item) {
        if (n == null) {
            numItems += 1;
            return new Node(item);
        }
        int c = item.compareTo(n.data);
        if (c < 0) {
            n.left = add(n.left, item);
        } else if (c > 0) {
            n.right = add(n.right, item);
        }

        recomputeHeight(n);
        recomputeSize(n);

        return n;
    }

    // Return the Node with the minimum value
    // in the given subtree.
    private Node min(Node n) {
        if (n == null) return null;
        while (n.left != null) {
            n = n.left;
        }
        return n;
    }

    // Return the Node with the maximum value
    // in the given subtree.
    private Node max(Node n) {
        if (n == null) return null;
        while (n.right != null) {
            n = n.right;
        }
        return n;
    }

    private Node deleteMin(Node n) {

        // If the left-hand child is null, then
        // n IS the minimum node.
        if (n.left == null) {
            return n.right;
        }

        // Otherwise, we recurse down the left-hand
        // side of the tree, fixing up sizes and
        // heights as we come back up.
        n.left = deleteMin(n.left);
        recomputeHeight(n);
        recomputeSize(n);

        return n;
    }

    // The removal code here closely follows that found in
    //       https://mathcenter.oxford.emory.edu/site/cs171/hibbardDeletion/
    // This may actually be Sedgewick and Wayne's algorithm;
    // I haven't bothered to check yet.
    // I was going to use the algorithm from Goodrich, but
    // that doesn't make it easy to fix up the sizes and
    // heights of the nodes running down the left branch
    // of the right-hand child of the node being deleted,
    // as is done here in deleteMin().

    // Remove works similarly to add(), in that it attempts
    // to delete 'item' from the tree rooted at 'n'.  It
    // returns the (potentially new) root node of the
    // searched subtree.
    private Node remove(Node n, T item) {

        // If we have an empty subtree, we didn't find
        // the item, and we just return.  The stack of
        // recursive calls will unwind with no changes
        // made to the tree.
        if (n == null) {
            return null;
        }

		// If this node does not have the key,
		// search down the left or right tree.
		int c = n.data.compareTo(item);
		if (c < 0)
			n.right = remove(n.right, item);
		else if (c > 0)
			n.left  = remove(n.left,  item);
        else {
            // If either child is null, this is trivial; we just
            // replace node n with the other sibling.  Note that
            // also handles the case where both children are null.
            if (n.left == null) {
                Node right = n.right;
                n.left = n.right = null;    // avoid loitering
                n = right;
            } else if (n.right == null) {
                Node left = n.left;
                n.left = n.right = null;    // avoid loitering
                n = left;
            } else {
                // Otherwise, we have to do a bit more work.
                Node t = n;
                n = min(t.right);
                n.right = deleteMin(t.right);
                n.left = t.left;
            }
            numItems -= 1;
        }

        if (n != null) {
            recomputeHeight(n);
            recomputeSize(n);
        }

        return n;
    }

    @Override
    public void add(T item) {
        root = add(root, item);
    }

    @Override
    public boolean contains(T item) {
        Node n = root;
        while (n != null) {
            int c = item.compareTo(n.data);
            if (c < 0) {
                n = n.left;
            } else if (c > 0) {
                n = n.right;
            } else {
                return true;
            }
        }
        return false;
    }

    @Override
    public void remove(T item) {
        root = remove(root, item);
    }

    @Override
    public int size() {
        return numItems;
    }

    @Override
    public boolean isEmpty() {
        return numItems == 0;
    }

    @Override
    public T min() {
        Node n = min(root);
        return n == null ? null : n.data;
    }

    @Override
    public T max() {
        Node n = max(root);
        return n == null ? null : n.data;
    }

    @Override
    public int index(T item) {
        int numFewer = 0;
        Node n = root;
        while (n != null) {
            int c = item.compareTo(n.data);
            if (c < 0) {
                n = n.left;
            } else if (c > 0) {
                numFewer += size(n.left) + 1;
                n = n.right;
            } else {
                return numFewer + size(n.left);
            }
        }
        return numFewer;
    }

    @Override
    public T select(int idx) {
        Node n = root;
        while (n != null) {
            int leftSize = size(n.left);
            if (leftSize == idx) {
                return n.data;
            } else if (leftSize > idx) {
                n = n.left;
            } else {
                idx -= leftSize + 1;
                n = n.right;
            }
        }
        return null;
    }

    @Override
    public T lowerBound(T item) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'lowerBound'");
    }

    @Override
    public T upperBound(T item) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'upperBound'");
    }

    private void inOrder(Node n, ArrayList<T> a) {
        if (n == null) return;
        inOrder(n.left, a);
        a.add(n.data);
        inOrder(n.right, a);
    }

    @Override
    public Iterator<T> iterator() {
        // It would preferable to have an iterator specific to this
        // class that could keep track of the current node and use
        // a successor() method to move to the next one.  Maybe later.
        ArrayList<T> a = new ArrayList<T>(numItems);
        inOrder(root, a);
        return a.iterator();
    }

    @Override
    public Iterator<T> items(T lo, T hi) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'items'");
    }
	
	public static void main(String[] args) {
		Set<String> set = new BSTSet<>();
		InteractiveCollectionTests.testSet(set);
	}
}
