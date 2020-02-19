package edu.normandale.csn;

/**
 * The Queue interface defines the API for a classic queue.
 * This implements the Queue API defined by Sedgewick and Wayne
 * in _Algorithms, 4th edition_.
 * <br><br>
 * A queue is a collection of objects that can be added to and
 * removed from, with the rule that the least recently added item
 * is the first one removed.  It is a FIFO (first in, first out)
 * collection.
 * <br>
 * The 'tail' of the queue is where items are added; the 'head' of
 * the queue is where they are removed from.  (Sedgewick and Wayne
 * use 'front' and 'back' in place of 'head' and 'tail' respectively.)
 * 
 * @author rdwells
 *
 * @param <T>	The type of object to store in the stack.
 */
public interface Queue<T> extends Iterable<T> {

	/**
	 * Adds an item to the tail of the queue.
	 * 
	 * @param item		The item to add.
	 */
	void enqueue(T item);
	
	/**
	 * Removes an item from the head of the queue,
	 * and returns it.
	 * 
	 * @return		The item removed from the head of the queue.
	 */
	T dequeue();
	
	/**
	 * Determines if the queue is empty.
	 * 
	 * @return		true if the queue is empty, false otherwise.
	 */
	boolean isEmpty();
	
	/**
	 * Returns the size of the queue.
	 * 
	 * @return		The number of items in the queue.
	 */
	int size();
}
