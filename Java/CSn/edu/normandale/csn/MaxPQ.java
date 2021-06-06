package edu.normandale.csn;

public interface MaxPQ<T extends Comparable<T>> {
	void insert(T value);
	T max();
	T delMax();
	boolean isEmpty();
	int size();
}
