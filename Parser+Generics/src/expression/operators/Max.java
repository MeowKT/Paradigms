package expression.operators;

import expression.operation.Operation;

public class Max<T> extends AbstractBinaryOperator <T> {

    public Max(TripleExpression<T> left, TripleExpression<T> right, Operation<T> op) {
        super(left, right, op);
    }

    @Override
    protected String getOperator() {
        return " max ";
    }

    @Override
    protected T calc(T x, T y) {
        return op.max(x, y);
    }

}
