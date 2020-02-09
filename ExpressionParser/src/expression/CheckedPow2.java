package expression;

public class CheckedPow2 implements CommonExpression {
    private CommonExpression expression;

    public CheckedPow2(CommonExpression expression) {
        this.expression = expression;
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

    private static int operate(int x) {
        checkValid(x);
        return (1 << x);
    }

    private static void checkValid(int x) {
        if (x == Integer.MIN_VALUE) {
            throw new NegateOverflowException(x);
        }
    }

    @Override
    public int evaluate(int x) {
        return operate(expression.evaluate(x));
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return operate(expression.evaluate(x, y, z));
    }
}
