package expression.operators;

import expression.exceptions.ComputationalException;
import expression.operation.Operation;

abstract class AbstractBinaryOperator implements GenericExpression {
    private GenericExpression left;
    private GenericExpression right;

    protected abstract <T> T calc(T x, T y, Operation<T> op) throws ComputationalException;

    protected abstract String getOperator();

    public AbstractBinaryOperator(GenericExpression left, GenericExpression right) {
        this.left = left;
        this.right = right;
    }

    public <T> T evaluate(T x, T y, T z, Operation<T> op) throws ComputationalException {
        return calc(left.evaluate(x, y, z, op), right.evaluate(x, y, z, op), op);
    }

    @Override
    public String toString() {
        return "(" + left + getOperator() + right + ")";
    }

}
