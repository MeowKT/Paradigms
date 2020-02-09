package expression;

abstract class AbstractBinaryOperator implements CommonExpression {
    private CommonExpression left;
    private CommonExpression right;

    protected abstract String getOperator();

    protected abstract int getPriority();

    protected abstract int calc(int x, int y);

    protected abstract boolean checkSpecialOperator();

    public AbstractBinaryOperator(CommonExpression left, CommonExpression right) {
        this.left = left;
        this.right = right;
    }

    public int evaluate(int x) {
        return calc(left.evaluate(x), right.evaluate(x));
    }
    public int evaluate(int x, int y, int z) {
        return calc(left.evaluate(x, y, z), right.evaluate(x, y, z));
    }

    private String getExpression(Expression expression, boolean isBracket) {
        return (isBracket ? "(" : "") + expression.toMiniString() + (isBracket ? ")" : "");
    }

    private boolean checkSimpleBrackets(Expression expression) {
        return expression instanceof AbstractBinaryOperator
                && ((AbstractBinaryOperator) expression).getPriority() < this.getPriority();
    }

    private boolean checkHardBrackets(Expression expression) {
        return (
                expression instanceof AbstractBinaryOperator
                        && ((AbstractBinaryOperator) expression).checkSpecialOperator()
                        && this.getPriority() == ((AbstractBinaryOperator) expression).getPriority()
        ) || (this.checkSpecialOperator()
                && expression instanceof AbstractBinaryOperator
                && ((AbstractBinaryOperator) expression).getPriority() <= this.getPriority()
        );
    }


    @Override
    public String toMiniString() {
        return getExpression(left, checkSimpleBrackets(left))
                + getOperator()
                + getExpression(right, checkSimpleBrackets(right) || checkHardBrackets(right));
    }

    @Override
    public String toString() {
        return "(" + left + getOperator() + right + ")";
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        AbstractBinaryOperator binaryOperator = (AbstractBinaryOperator) object;
        return this.left.equals(binaryOperator.left) && right.equals(binaryOperator.right);
    }

    @Override
    public int hashCode() {
        return 13 * left.hashCode() + 43 * getClass().hashCode() + 41 * right.hashCode();
    }
}
