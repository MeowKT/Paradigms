package expression.operators;

import expression.exceptions.ComputationalException;
import expression.operation.Operation;

abstract class AbstractUnaryOperator<T> implements TripleExpression<T> {
    protected TripleExpression<T> expression;
    protected final Operation<T> op;

    protected abstract String getOperator();

    protected abstract T operate(T x) throws ComputationalException;

    public AbstractUnaryOperator(TripleExpression<T> expression, Operation<T> op) {
        this.expression = expression;
        this.op = op;
    }

    public T evaluate(T x, T y, T z) throws ComputationalException {
        return operate(expression.evaluate(x, y, z));
    }

    @Override
    public String toString() {
        return getOperator() + " " + expression;
    }
}
