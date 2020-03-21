package expression.operators;

import expression.operation.Operation;

public class Multiply extends AbstractBinaryOperator {

    public Multiply(GenericExpression left, GenericExpression right) {
        super(left, right);
    }
    @Override
    protected String getOperator() {
        return " * ";
    }

    @Override
    protected <T> T calc(T x, T y, Operation<T> op) {
        return op.multiply(x, y);
    }

}
