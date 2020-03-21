package expression.operators;

import expression.exceptions.ComputationalException;
import expression.operation.Operation;

public interface GenericExpression {
    <T> T evaluate(T x, T y, T z, Operation<T> op) throws ComputationalException;
}
