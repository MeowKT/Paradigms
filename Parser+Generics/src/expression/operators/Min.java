package expression.operators;

import expression.operation.Operation;

public class Min<T> extends AbstractBinaryOperator <T> {

    public Min(TripleExpression<T> left, TripleExpression<T> right, Operation<T> op) {
        super(left, right, op);
    }

    @Override
    protected String getOperator() {
        return " min ";
    }

    @Override
    protected T calc(T x, T y) {
        return op.min(x, y);
    }

}
