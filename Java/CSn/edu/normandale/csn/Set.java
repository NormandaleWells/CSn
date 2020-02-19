package edu.normandale.csn;

/**
 * The Set interface defines the API for an unordered set.
 * This API is based on the SET API defined by
 * Sedgewick and Wayne in _Algorithms, 4th edition_.
 * <br><br>
 * A Set is a collection of unique values of some type T.
 * Attempting to add an item that is already present in
 * the set is a no-op.
 * <br><br>
 * A Set is an iterable object; there is no guarantee of
 * the order in which the elements are iterated.  The
 * order could even change between iterations over the
 * the same set.
 * <br><br>
 * This API makes no guarantees of complexity for the
 * add(), contains(), and remove() methods; see the
 * implementing class for those details.  isEmpty()
 * and size() are always constant time functions.
 * 
 * @author rdwells
 *
 * @param <T>	The type of values stored in the Set.
 */
public interface Set<T> extends Iterable<T> {

	/**
	 * Adds an item to the set.  If the item is already
	 * in the set, nothing happens.
	 * 
	 * @param item		The item to add to the set.
	 */
	void add(T item);

	/**
	 * Checks to see if the set contains an entry for
	 * the given key.
	 * 
	 * @param item		The key to search for.
	 * @return			<tt>true</tt> if the key is found, <tt>false</tt> otherwise.
	 */
	boolean contains(T item);
	
	/**
	 * Removes the given item from the set.  If the item
	 * is not in the set, nothing happens.
	 * 
	 * @param item		The item to remove.
	 */
	void remove(T item);
	
	/**
	 * Returns the number of items in the set.
	 * 
	 * @return	The number of items in the set.
	 */
	int size();
	
	/**
	 * Checks to see if the set is empty.
	 * 
	 * @return		<tt>true<tt> if the set is empty, <tt>false<tt> otherwise.
	 */
	boolean isEmpty();
}
