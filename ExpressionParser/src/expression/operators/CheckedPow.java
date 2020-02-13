package expression.operators;

import expression.exceptions.IllegalOperationException;
import expression.exceptions.OverflowException;
import expression.parser.IntegerOperation;

public class CheckedPow extends AbstractBinaryOperator {

    public CheckedPow(CommonExpression left, CommonExpression right) {
        super(left, right);
    }

    @Override
    protected String getOperator() {
        return " ** ";
    }

    @Override
    public int getPriority() {
        return 2;
    }

    @Override
    protected int calc(int x, int y) {
        return IntegerOperation.safePow(x, y);
    }

    @Override
    protected boolean checkSpecialOperator() {
        return false;
    }
}
