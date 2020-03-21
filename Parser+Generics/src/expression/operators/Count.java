package expression.operators;

import expression.exceptions.OverflowException;
import expression.operation.Operation;

public class Count extends AbstractUnaryOperator {

    public Count(GenericExpression expression) {
        super(expression);
    }

    @Override
    public <T> T operate(T x, Operation<T> op) throws OverflowException {
        return op.count(x);
    }

    @Override
    protected String getOperator() {
        return "count";
    }

}
