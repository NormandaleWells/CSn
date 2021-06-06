package edu.normandale.csn;

public interface MinPQ<T extends Comparable<T>> {
	void insert(T value);
	T min();
	T delMin();
	boolean isEmpty();
	int size();
}
