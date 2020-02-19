package edu.normandale.csn;

public interface Graph<T> {
	Iterable<T> adjacent(T v);
}
