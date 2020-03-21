package expression.operators;

import expression.operation.Operation;

public class Add extends AbstractBinaryOperator {

    public Add(GenericExpression left, GenericExpression right) {
        super(left, right);
    }

    @Override
    protected String getOperator() {
        return " + ";
    }

    @Override
    protected <T> T calc(T x, T y, Operation<T> op) {
        return op.add(x, y);
    }

}
