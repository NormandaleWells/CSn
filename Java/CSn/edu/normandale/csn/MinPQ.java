package edu.normandale.csn;

public interface MinPQ<T extends Comparable<T>> {
	public void insert(T value);
	public T min();
	public T delMin();
	public boolean isEmpty();
	public int size();
}
