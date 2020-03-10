package expression.operators;

import expression.operation.Operation;

public class Divide<T> extends AbstractBinaryOperator <T> {

    public Divide(TripleExpression<T> left, TripleExpression<T> right, Operation<T> op) {
        super(left, right, op);
    }

    @Override
    protected String getOperator() {
        return " / ";
    }

    @Override
    protected T calc(T x, T y) {
        return op.divide(x, y);
    }

}
