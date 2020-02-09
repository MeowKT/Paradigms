package expression;

import expression.parser.ParserException;

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
        checkValid(x, y);
        return x - y;
    }

    @Override
    protected boolean checkSpecialOperator() {
        return true;
    }

    void checkValid(int x, int y) {
        if (y < 0 && x > Integer.MAX_VALUE + y) {
            throw new SubtractOverflowException(x, y);
        } else if (y > 0 && x < Integer.MIN_VALUE + y) {
            throw new SubtractOverflowException(x, y);
        }
    }
}
