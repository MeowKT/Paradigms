package expression.operators;

import expression.exceptions.ComputationalException;
import expression.exceptions.IllegalOperationException;
import expression.exceptions.OverflowException;
import expression.parser.IntegerOperation;

public class CheckedPow2 extends AbstractUnaryOperator {

    public CheckedPow2(CommonExpression expression) {
        super(expression);
    }

    @Override
    protected String getOperator() {
        return "pow2";
    }

    @Override
    protected int getPriority() {
        return 2;
    }

    @Override
    protected int operate(int x) {
        return IntegerOperation.safePow(2, x);
    }

    @Override
    protected boolean checkSpecialOperator() {
        return false;
    }
}
