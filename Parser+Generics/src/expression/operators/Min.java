package expression.operators;

import expression.operation.Operation;

public class Min extends AbstractBinaryOperator  {

    public Min(GenericExpression left, GenericExpression right) {
        super(left, right);
    }

    @Override
    protected String getOperator() {
        return " min ";
    }

    @Override
    protected <T> T calc(T x, T y, Operation<T> op) {
        return op.min(x, y);
    }

}
