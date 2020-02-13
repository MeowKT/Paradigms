package expression.operators;

import expression.exceptions.IllegalOperationException;
import expression.exceptions.OverflowException;
import expression.parser.IntegerOperation;

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
        return IntegerOperation.safeDivide(x, y);
    }

    @Override
    protected boolean checkSpecialOperator() {
        return true;
    }

}
