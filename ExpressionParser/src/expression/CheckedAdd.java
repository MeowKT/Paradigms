package expression;

import expression.parser.ParserException;

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
        checkValid(x, y);
        return x + y;
    }

    void checkValid(int x, int y) {
        if (y > 0 && Integer.MAX_VALUE - y < x) {
            throw new AddOverflowException(x, y);
        } else if (y < 0 && Integer.MIN_VALUE - y > x) {
            throw new AddOverflowException(x, y);
        }
    }

    @Override
    protected boolean checkSpecialOperator() {
        return false;
    }
}
