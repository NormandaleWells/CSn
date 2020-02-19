package edu.normandale.csn;

public interface MaxPQ<T extends Comparable<T>> {
	public void insert(T value);
	public T max();
	public T delMax();
	public boolean isEmpty();
	public int size();
}
