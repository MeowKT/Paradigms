package expression.operators;

import expression.exceptions.ComputationalException;
import expression.operation.Operation;

abstract class AbstractUnaryOperator implements GenericExpression {
    protected GenericExpression expression;

    protected abstract String getOperator();

    protected abstract <T> T operate(T x, Operation<T> op) throws ComputationalException;

    public AbstractUnaryOperator(GenericExpression expression) {
        this.expression = expression;
    }

    public <T> T evaluate(T x, T y, T z, Operation<T> op) throws ComputationalException {
        return operate(expression.evaluate(x, y, z, op), op);
    }

    @Override
    public String toString() {
        return getOperator() + " " + expression;
    }
}
