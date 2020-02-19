package edu.normandale.csn;

public interface BinaryOp<T1, T2, Ret> {
	Ret op(T1 t1, T2 t2);
}
