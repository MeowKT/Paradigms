package expression.operators;

import expression.exceptions.ComputationalException;

import java.util.Objects;

abstract class AbstractUnaryOperator implements CommonExpression {
    private CommonExpression expression;

    protected abstract String getOperator();

    protected abstract int getPriority();

    protected abstract int operate(int x) throws ComputationalException;

    protected abstract boolean checkSpecialOperator();

    public AbstractUnaryOperator(CommonExpression expression) {
        this.expression = expression;
    }

    public int evaluate(int x, int y, int z) throws ComputationalException {
        return operate(expression.evaluate(x, y, z));
    }

    private String getExpression(CommonExpression expression, boolean isBracket) {
        return (isBracket ? "(" : "") + expression.toMiniString() + (isBracket ? ")" : "");
    }

    @Override
    public String toMiniString() {
        return getOperator() + " " + expression;
    }

    @Override
    public String toString() {
        return getOperator() + " " + expression;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractUnaryOperator that = (AbstractUnaryOperator) o;
        return Objects.equals(expression, that.expression);
    }

    @Override
    public int hashCode() {
        return Objects.hash(expression);
    }
}
