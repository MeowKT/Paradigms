package expression.operators;

import expression.exceptions.OverflowException;
import expression.parser.IntegerOperation;

public class CheckedMultiply extends AbstractBinaryOperator {

    public CheckedMultiply(CommonExpression left, CommonExpression right) {
        super(left, right);
    }

    @Override
    protected String getOperator() {
        return " * ";
    }

    @Override
    public int getPriority() {
        return 2;
    }

    @Override
    protected int calc(int x, int y) {
        return IntegerOperation.safeMultiply(x, y);
    }

    @Override
    protected boolean checkSpecialOperator() {
        return false;
    }
}
