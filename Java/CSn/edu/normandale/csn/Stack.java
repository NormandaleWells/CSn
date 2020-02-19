package edu.normandale.csn;

/**
 * The Stack interface defines the API for a classic stack.
 * This API is based on the Stack API defined by Sedgewick
 * and Wayne in _Algorithms, 4th edition_.
 * <br><br>
 * A stack is a collection of objects that can be added to and
 * removed from, with the rule that the most recently added item
 * is the first one removed.  It is a LIFO (last in, first out)
 * collection.
 * <br>
 * The next item to be removed from the stack -- which is also the
 * one most recently added -- is called the 'top' item.
 * 
 * @author rdwells
 *
 * @param <T>	The type of object to store in the stack.
 */
public interface Stack<T> extends Iterable<T> {

	/**
	 * Adds an item to the top of the stack.
	 * 
	 * @param item		The item to add to the stack.
	 */
	void push(T item);
	
	/**
	 * Removes the most recently added item from the top of the
	 * stack, and returns it.
	 * 
	 * @return			The item removed from the stack.
	 */
	T pop();
	
	/**
	 * Returns the most recently added item without removing it
	 * from the stack.
	 * 
	 * @return			The most recently added item.
	 */
	T peek();

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
