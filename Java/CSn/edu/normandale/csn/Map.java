package edu.normandale.csn;

/**
 * The SymbolTable interface defines the API for a symbol
 * table.  This API is based on the ST API defined by
 * Sedgewick and Wayne in _Algorithms, 4th edition_.
 * <br><br>
 * A symbol table is a collection of key/value pairs that
 * allows lookup of a value based on a given key.  A given
 * key may have only a single value (that is, this is not a
 * multimap), but that value may be a collection type.
 * <br><br>
 * This API makes no guarantees of complexity for any
 * method; see the implementing class for those details.
 * 
 * @author rdwells
 *
 * @param <Key>	The type used for the keys in the table.
 * @param <Value> The type used for the values in the table.
 */
interface Map<Key,Value> {

	/**
	 * Adds or updates an entry to the symbol table.  If
	 * the given key already has an associated value,
	 * the value is simply updated without a new table
	 * entry being created.
	 * 
	 * @param k		The key for the new item.
	 * @param v		The value to associate with the key.
	 */
	void put(Key k, Value v);
	
	/**
	 * Retrieves the value associated with the given key.
	 * If there is no entry for this key, <tt>null</tt>
	 * is returned.
	 * 
	 * @param k		The key to search for.
	 * @return		The value associated with the given key.
	 */
	Value get(Key k);
	
	/**
	 * Checks to see if the symbol table contains an entry
	 * for the given key.
	 * 
	 * @param k		The key to search for.
	 * @return		<tt>true</tt> if the key is found, <tt>false</tt> otherwise.
	 */
	boolean contains(Key k);
	
	/**
	 * Removes the key and its associated value from the table.
	 * There is no indication returned if there is no entry
	 * the given key.
	 * 
	 * @param k		The key to remove.
	 */
	void delete(Key k);
	
	/**
	 * Returns the number of key/value pairs in the symbol table.
	 * 
	 * @return		The number of entries in the table.
	 */
	int size();
	
	/**
	 * Checks to see if the symbol table is empty (has no
	 * key/value pairs).
	 * 
	 * @return	<tt>true</tt> if table is empty, <tt>false</tt> otherwise.
	 */
	boolean isEmpty();
	
	/**
	 * Returns an iterator that may be used to iterate over
	 * all the keys in the table.
	 * 
	 * @return		An iterator allowing full traversal of the table.
	 */
	Iterable<Key> keys();
}
