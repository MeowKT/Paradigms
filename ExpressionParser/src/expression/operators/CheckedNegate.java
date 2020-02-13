package expression.operators;

import expression.exceptions.ComputationalException;
import expression.exceptions.OverflowException;
import expression.parser.IntegerOperation;

public class CheckedNegate implements CommonExpression {
    private CommonExpression expression;

    public CheckedNegate(CommonExpression expression) {
        this.expression = expression;
    }

    private static int operate(int x) throws OverflowException {
        return IntegerOperation.safeMultiply(x, -1);
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return operate(expression.evaluate(x, y, z));
    }

    @Override
    public String toString() {
        return "-(" + expression.toString() + ")";
    }

    @Override
    public String toMiniString() {
        boolean hasBrackets = expression instanceof AbstractBinaryOperator;
        return "-" + (hasBrackets ? "(" : "") + expression.toMiniString() + (hasBrackets ? ")" : "");
    }
}
