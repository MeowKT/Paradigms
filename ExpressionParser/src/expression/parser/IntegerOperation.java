package expression.parser;

import expression.exceptions.ComputationalException;
import expression.exceptions.IllegalOperationException;
import expression.exceptions.OverflowException;

import static java.lang.Integer.signum;
import static java.lang.Math.abs;


public abstract class IntegerOperation {

    public static int add(int x, int y) {
        return x + y;
    }

    public static int safeAdd(int x, int y) {
        if (y > 0 && Integer.MAX_VALUE - y < x) {
            throw new OverflowException("Add");
        } else if (y < 0 && Integer.MIN_VALUE - y > x) {
            throw new OverflowException("Add");
        }
        return add(x, y);
    }

    public static int subtract(int x, int y) {
        return x - y;
    }

    public static int safeSubtract(int x, int y) {
        if (y < 0 && x > Integer.MAX_VALUE + y) {
            throw new OverflowException("Subtract");
        } else if (y > 0 && x < Integer.MIN_VALUE + y) {
            throw new OverflowException("Subtract");
        }
        return subtract(x, y);
    }

    public static int divide(int x, int y) {
        return x / y;
    }

    public static int safeDivide(int x, int y) {
        if (y == 0) {
            throw new IllegalOperationException("Divide by zero");
        }
        if (x == Integer.MIN_VALUE && y == -1) {
            throw new OverflowException("Divide");
        }
        return divide(x, y);
    }

    public static int multiply(int x, int y) {
        return x * y;
    }

    public static int safeMultiply(int x, int y) {
        if (x == 0 || y == 0 || x == 1 || y == 1) {
            return multiply(x, y);
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
        return multiply(x, y);
    }

    public static int safePow(int x, int y) {
        if (x == 0 && y == 0) {
            throw new IllegalOperationException("0 ** 0 is not determinate");
        }

        if (y < 0) {
            throw new IllegalOperationException("Negative power");
        }

        if (Math.abs(x) <= 1 && y > 2) {
            y = (y - 1) % 2 + 1; /// faster then binpow
        }

        if (x == 2) {
            if (y >= 31) {
                throw new OverflowException("Pow2 Overflow");
            }
            return 1 << y;
        }

        try {
            int result = 1;
            while (y > 0) {
                if ((y & 1) == 1) {
                    result = safeMultiply(result, x);
                }
                if (y > 1) {
                    x = safeMultiply(x, x);
                }
                y >>= 1;
            }
            return result;
        } catch (OverflowException e) {
            throw new OverflowException("Pow");
        }
    }


    public static int log(int x, int y) {
        int result = 0, current = 1;
        while (current <= x / y) {
            current *= y;
            result++;
        }
        return result;
    }

    public static int safeLog(int x, int y) {
        if (y <= 0 || y == 1) {
            throw new IllegalOperationException("Incorrect log base");
        }
        if (x <= 0) {
            throw new IllegalOperationException("Under the logarithm sign is a negative number");
        }
        return log(x, y);
    }
}
