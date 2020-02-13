package expression.operators;

import expression.exceptions.OverflowException;
import expression.parser.IntegerOperation;

public class CheckedSubtract extends AbstractBinaryOperator {

    public CheckedSubtract(CommonExpression left, CommonExpression right) {
        super(left, right);
    }

    @Override
    protected String getOperator() {
        return " - ";
    }

    @Override
    public int getPriority() {
        return 1;
    }

    @Override
    protected int calc(int x, int y) {
        return IntegerOperation.safeSubtract(x, y);
    }

    @Override
    protected boolean checkSpecialOperator() {
        return true;
    }
}
