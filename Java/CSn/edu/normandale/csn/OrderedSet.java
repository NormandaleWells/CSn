package edu.normandale.csn;

import java.util.Iterator;

/**
 * The OrderedSet interface defines the API for an ordered
 * set, as an extension of the Set API.  This API is based
 * on the ST API defined by Sedgewick and Wayne in
 * _Algorithms, 4th edition_.
 * <br><br>
 * A ordered assumes that the set items are maintained
 * in sorted ordered, as defined by the T.compareTo()
 * method.
 * <br><br>
 * This API makes no guarantees of complexity for any
 * method; see the implementing class for those details.
 * <br><br>
 * The most significant difference between this API and
 * the one used by Sedgewick and Wayne is that we've
 * replaced their <tt>floor()</tt> and <tt>ceiling()</tt>
 * methods with <tt>lowerBound()</tt> and <tt>upperBound()</tt>.
 * This makes it easier to iterator over adjacent (half-open)
 * subranges without having to worry about the last element
 * of one subrange overlapping with with first element of
 * the next one.
 * 
 * @author rdwells
 *
 * @param <T>	The type used for the values in the table.
 */
interface OrderedSet<T extends Comparable<T>> extends Set<T> {
	/**
	 * Returns the minimum value in the set, or <tt>null</tt> if the table is empty.
	 * 
	 * @return		The minimum value of any value in the table.
	 */
	T min();
	
	/**
	 * Returns the maximum value in the set, or <tt>null</tt> if the table is empty.
	 * 
	 * @return		The maximum value of any value in the table.
	 */
	T max();
	
    /**
     * Returns the index of the given item; that is, the number of items
     * smaller than the given item.  The item need not be in the set.
     * 
     * @param item  The item to get the index of.
     * @return      The index of the given item.
     */
    int index(T item);

    /**
     * Returns the item whose index is the given integer.  That is, the
     * item that would be index <tt>idx<tt> if the all the set
     * items were in an ordered array.
     * 
     * @param idx   The idx of the item to return.
     * @return      The item at the given index.
     */
    T select(int idx);

	/**
	 * Returns the smallest value greater than or equal to the
	 * given value. If all the values in the table are less than
	 * the given value, <tt>null</tt> is returned.
	 * 
	 * @param k		The value to search for.
	 * @return		The smallest value greater than or equal to the given value.
	 */
	T lowerBound(T item);
	
	/**
	 * Returns the smallest value strictly greater than the
	 * given value. If all the values in the table are less than
	 * the given value, <tt>null</tt> is returned.
	 * 
	 * @param k		The value to search for.
	 * @return		The smallest value strictly greater than the given value.
	 */
	T upperBound(T item);
	
	/**
	 * Returns an iterator that may be used to iterate over
	 * all values in the closed range [lo,hi).  If <tt>hi</tt>
	 * is <tt>null</tt>, the upper bound of the iteration is
	 * beyond the last element.
	 * 
	 * @param lo	The lower bound of the iteration.
	 * @param hi	The upper bound of the iteration.
	 * @return		An iterator that allows iterating all the values in the given range.
	 */
	Iterator<T> items(T lo, T hi);
}
