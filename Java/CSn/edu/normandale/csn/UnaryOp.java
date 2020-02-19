package edu.normandale.csn;

public interface UnaryOp<T, Ret> {
	Ret op(T t);
}
