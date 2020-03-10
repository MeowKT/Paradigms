package expression.operators;

import expression.operation.Operation;

public class Multiply<T> extends AbstractBinaryOperator<T> {

    public Multiply(TripleExpression<T> left, TripleExpression<T> right, Operation<T> op) {
        super(left, right, op);
    }
    @Override
    protected String getOperator() {
        return " * ";
    }

    @Override
    protected T calc(T x, T y) {
        return op.multiply(x, y);
    }

}
