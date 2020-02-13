package expression.operators;

import expression.exceptions.IllegalOperationException;
import expression.parser.IntegerOperation;

public class CheckedLog extends AbstractBinaryOperator {

    public CheckedLog(CommonExpression left, CommonExpression right) {
        super(left, right);
    }

    @Override
    protected String getOperator() {
        return " // ";
    }

    @Override
    public int getPriority() {
        return 2;
    }

    @Override
    protected int calc(int x, int y) {
        return IntegerOperation.safeLog(x, y);
    }

    @Override
    protected boolean checkSpecialOperator() {
        return false;
    }
}
