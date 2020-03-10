package expression.operation;


import expression.exceptions.IllegalOperationException;
import expression.exceptions.OverflowException;
import expression.operation.Operation;

import static java.lang.Integer.signum;
import static java.lang.Math.abs;


public class IntegerOperation implements Operation<Integer> {

    @Override
    public Integer parseNumber(String number) {
        return Integer.parseInt(number);
    }

    public Integer add(Integer x, Integer y) {
        if (y > 0 && Integer.MAX_VALUE - y < x) {
            throw new OverflowException("Add");
        } else if (y < 0 && Integer.MIN_VALUE - y > x) {
            throw new OverflowException("Add");
        }
        return x + y;
    }

    public Integer subtract(Integer x, Integer y) {
        if (y < 0 && x > Integer.MAX_VALUE + y) {
            throw new OverflowException("Subtract");
        } else if (y > 0 && x < Integer.MIN_VALUE + y) {
            throw new OverflowException("Subtract");
        }
        return x - y;
    }


    @Override
    public Integer negate(Integer x) {
        return -x;
    }

    @Override
    public Integer parse(String x) {
        return Integer.parseInt(x);
    }

    public Integer divide(Integer x, Integer y) {
        if (y == 0) {
            throw new IllegalOperationException("Divide by zero");
        }
        if (x == Integer.MIN_VALUE && y == -1) {
            throw new OverflowException("Divide");
        }
        return x / y;
    }

    public Integer multiply(Integer x, Integer y) {
        if (x == 0 || y == 0 || x == 1 || y == 1) {
            return x * y;
        }

        if (y == Integer.MIN_VALUE || x == Integer.MIN_VALUE) {
            throw new OverflowException("Multiply");
        }
        Integer sign = signum(x) * signum(y);
        if (sign > 0) {
            if (abs(x) > Integer.MAX_VALUE / abs(y)) {
                throw new OverflowException("Multiply");
            }
        } else {
            if (sign * abs(x) < Integer.MIN_VALUE / abs(y)) {
                throw new OverflowException("Multiply");
            }
        }
        return x * y;
    }

}
