package expression;

import static java.lang.Integer.signum;
import static java.lang.Math.abs;

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
        checkValid(x, y);
        return x * y;
    }

    void checkValid(int x, int y) {
        if (x == 0 || y == 0 || x == 1 || y == 1) {
            return;
        }
        if (y == Integer.MIN_VALUE || x == Integer.MIN_VALUE) {
            throw new MultiplyOverflowException(x, y);
        }
        int sign = signum(x) * signum(y);
        if (sign > 0) {
            if (abs(x) > Integer.MAX_VALUE / abs(y)) {
                throw new MultiplyOverflowException(x, y);
            }
        } else {
            if (sign * abs(x) < Integer.MIN_VALUE / abs(y)) {
                throw new MultiplyOverflowException(x, y);
            }
        }
    }

    @Override
    protected boolean checkSpecialOperator() {
        return false;
    }
}
