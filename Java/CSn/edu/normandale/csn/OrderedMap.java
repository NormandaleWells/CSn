package edu.normandale.csn;

/**
 * The OrderedST interface defines the API for an ordered
 * symbol table.  This API is based on the ST API defined
 * by Sedgewick and Wayne in _Algorithms, 4th edition_.
 * <br><br>
 * A symbol is a collection of key/value pairs that allows
 * lookup of a value based on a given key.  A given key
 * may have only a single value (that is, this is not a
 * multimap), but that value may be a collection type.
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
 * @param <Key>	The type used for the keys in the table.
 * @param <Value> The type used for the values in the table.
 */
public interface OrderedMap<Key extends Comparable<Key>, Value> extends Map<Key, Value> {
	/**
	 * Returns the minimum key in the table, or <tt>null</tt> if the table is empty.
	 * 
	 * @return		The minimum value of any key in the table.
	 */
	Key min();
	
	/**
	 * Returns the maximum key in the table, or <tt>null</tt> if the table is empty.
	 * 
	 * @return		The maximum value of any key in the table.
	 */
	Key max();
	
	/**
	 * Returns the smallest key greater than or equal to the
	 * given key.
	 * 
	 * @param k		The key to search for.
	 * @return		The smallest key greater than or equal to the given key.
	 */
	Key lowerBound(Key k);
	
	/**
	 * Returns the smallest key strictly greater than the
	 * given key. If all the keys in the table are less than
	 * the given key, <tt>null</tt> is returned.
	 * 
	 * @param k		The key to search for.
	 * @return		The smallest key strictly greater than the given key.
	 */
	Key upperBound(Key k);
	
	/**
	 * Returns an iterator that may be used to iterate over
	 * all keys in the closed range [lo,hi).  If <tt>hi</tt>
	 * is <tt>null</tt>, the upper bound of the iteration is
	 * beyond the last element.
	 * 
	 * @param lo	The lower bound of the iteration.
	 * @param hi	The upper bound of the iteration.
	 * @return		An iterator that allows iterating all the keys in the given range.
	 */
	Iterable<Key> keys(Key lo, Key hi);
}
