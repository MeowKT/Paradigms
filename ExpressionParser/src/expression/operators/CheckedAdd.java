package expression.operators;

import expression.exceptions.OverflowException;
import expression.parser.IntegerOperation;

public class CheckedAdd extends AbstractBinaryOperator {

    public CheckedAdd(CommonExpression left, CommonExpression right) {
        super(left, right);
    }

    @Override
    protected String getOperator() {
        return " + ";
    }

    @Override
    protected int getPriority() {
        return 1;
    }

    @Override
    protected int calc(int x, int y) {
        return IntegerOperation.safeAdd(x, y);
    }

    @Override
    protected boolean checkSpecialOperator() {
        return false;
    }
}
