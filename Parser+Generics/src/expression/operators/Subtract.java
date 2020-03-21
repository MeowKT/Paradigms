package expression.operators;

import expression.operation.Operation;

public class Subtract extends AbstractBinaryOperator {

    public Subtract(GenericExpression left, GenericExpression right) {
        super(left, right);
    }

    @Override
    protected String getOperator() {
        return " - ";
    }

    @Override
    protected <T> T calc(T x, T y, Operation<T> op) {
        return op.subtract(x, y);
    }
}
