package expression;

import expression.parser.ComputationalException;

public class DivideByZeroException extends ComputationalException {
    public DivideByZeroException(int x, int y) {
        super("Divide by zero: " + x + " / " + y);
    }
}
