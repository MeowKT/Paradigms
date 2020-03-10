package expression.operators;

import expression.exceptions.ComputationalException;

public interface TripleExpression<T> {
    T evaluate(T x, T y, T z) throws ComputationalException;
}