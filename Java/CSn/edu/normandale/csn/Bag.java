package edu.normandale.csn;

/**
 * The Bag interface defines the API for a classic bag (unordered
 * set).
 * This implements the Bag API defined by Sedgewick and Wayne
 * in _Algorithms, 4th edition_.
 * <br><br>
 * A bag is a collection of objects that can be added to but not
 * removed from.
 * 
 * @author rdwells
 *
 * @param <T>	The type of object to store in the stack.
 */
public interface Bag<T> extends Iterable<T> {

	/**
	 * Adds an item to the top of the stack.
	 * 
	 * @param item		The item to add to the stack.
	 */
	void add(T item);

	/**
	 * Determines whether the stack is empty or not.
	 * 
	 * @return		True if the stack is empty, false otherwise.
	 */
	boolean isEmpty();
	
	/**
	 * Determines the number of items in the stack.
	 * 
	 * @return		The number of items in the stack.
	 */
	int size();
}
