package edu.normandale.csn;

import java.util.Comparator;

// WrapComparable is a helper class that makes it easier to
// implement functions that use a Comparable in terms of a
// version that takes a Comparator.
public class WrapComparable<T extends Comparable<T>> implements Comparator<T> {
	@Override
	public int compare(T o1, T o2) {
		return o1.compareTo(o2);
	}
}
