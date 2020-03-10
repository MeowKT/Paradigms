package expression.operators;

import expression.operation.Operation;

public class Add<T> extends AbstractBinaryOperator<T> {

    public Add(TripleExpression<T> left, TripleExpression<T> right, final Operation<T> op) {
        super(left, right, op);
    }

    @Override
    protected String getOperator() {
        return " + ";
    }

    @Override
    protected T calc(T x, T y) {
        return op.add(x, y);
    }

}
