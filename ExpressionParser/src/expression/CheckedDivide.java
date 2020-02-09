package expression;

import static java.lang.Integer.signum;
import static java.lang.Math.abs;

public class CheckedDivide extends AbstractBinaryOperator {

    public CheckedDivide(CommonExpression left, CommonExpression right) {
        super(left, right);
    }

    @Override
    protected String getOperator() {
        return " / ";
    }

    @Override
    protected int getPriority() {
        return 2;
    }

    @Override
    protected int calc(int x, int y) {
        checkValid(x, y);
        return x / y;
    }
    void checkValid(int x, int y) {
        if (y == 0) {
            throw new DivideByZeroException(x, y);
        }
        if (x == Integer.MIN_VALUE && y == -1) {
            throw new DivideOverflowException(x, y);
        }
    }
    @Override
    protected boolean checkSpecialOperator() {
        return true;
    }

}
