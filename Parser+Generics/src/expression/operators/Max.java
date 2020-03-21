package expression.operators;

import expression.operation.Operation;

public class Max extends AbstractBinaryOperator  {

    public Max(GenericExpression left, GenericExpression right) {
        super(left, right);
    }

    @Override
    protected String getOperator() {
        return " max ";
    }

    @Override
    protected <T> T calc(T x, T y, Operation<T> op) {
        return op.max(x, y);
    }

}
