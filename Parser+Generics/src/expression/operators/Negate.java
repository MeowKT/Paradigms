package expression.operators;

import expression.exceptions.OverflowException;
import expression.operation.Operation;

public class Negate extends AbstractUnaryOperator {

    public Negate(GenericExpression expression, Operation op) {
        super(expression);
    }

    @Override
    public <T> T operate(T x, Operation<T> op) throws OverflowException {
        return op.negate(x);
    }

    @Override
    protected String getOperator() {
        return "-";
    }

}
