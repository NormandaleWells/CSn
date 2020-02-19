package edu.normandale.csn;

import java.util.function.Predicate;

// IsEqual is a helper class that makes it easier to implement
// functions that check for equality to a given value in terms
// of a version that takes a predicate.

public class IsEqual<T> implements Predicate<T> {
	private T value;

	public IsEqual(T value) {
		this.value = value;
	}

	@Override
	public boolean test(T t) {
		return t.equals(value);
	}
}
