package expression.operation;


import expression.exceptions.IllegalOperationException;
import expression.exceptions.OverflowException;

import static java.lang.Integer.signum;
import static java.lang.Math.abs;


public class IntegerOperation implements Operation<Integer> {

    boolean isChecked = false;

    public IntegerOperation(boolean isChecked) {
        this.isChecked = isChecked;
    }

    @Override
    public Integer add(Integer x, Integer y) {
        if (isChecked) {
            if (y > 0 && Integer.MAX_VALUE - y < x) {
                throw new OverflowException("Add");
            } else if (y < 0 && Integer.MIN_VALUE - y > x) {
                throw new OverflowException("Add");
            }
        }
        return x + y;
    }

    @Override
    public Integer subtract(Integer x, Integer y) {
        if (isChecked) {
            if (y < 0 && x > Integer.MAX_VALUE + y) {
                throw new OverflowException("Subtract");
            } else if (y > 0 && x < Integer.MIN_VALUE + y) {
                throw new OverflowException("Subtract");
            }
        }
        return x - y;
    }


    @Override
    public Integer negate(Integer x) {
        return multiply(x, -1);
    }

    @Override
    public Integer parse(String x) {
        return Integer.parseInt(x);
    }

    @Override
    public Integer min(Integer x, Integer y) {
        return Integer.min(x, y);
    }

    @Override
    public Integer max(Integer x, Integer y) {
        return Integer.max(x, y);
    }

    @Override
    public Integer count(Integer x) {
        return Integer.bitCount(x);
    }

    @Override
    public Integer divide(Integer x, Integer y) {
        if (y == 0) {
            throw new IllegalOperationException("Divide by zero");
        }
        if (isChecked) {
            if (x == Integer.MIN_VALUE && y == -1) {
                throw new OverflowException("Divide");
            }
        }
        return x / y;
    }

    @Override
    public Integer multiply(Integer x, Integer y) {
        if (isChecked) {
            if (x == 0 || y == 0 || x == 1 || y == 1) {
                return x * y;
            }

            if (y == Integer.MIN_VALUE || x == Integer.MIN_VALUE) {
                throw new OverflowException("Multiply");
            }
            int sign = signum(x) * signum(y);
            if (sign > 0) {
                if (abs(x) > Integer.MAX_VALUE / abs(y)) {
                    throw new OverflowException("Multiply");
                }
            } else {
                if (sign * abs(x) < Integer.MIN_VALUE / abs(y)) {
                    throw new OverflowException("Multiply");
                }
            }
        }
        return x * y;
    }

}
