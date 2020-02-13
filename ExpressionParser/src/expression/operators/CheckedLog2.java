package expression.operators;

import expression.exceptions.ComputationalException;
import expression.exceptions.IllegalOperationException;
import expression.parser.IntegerOperation;

public class CheckedLog2 extends AbstractUnaryOperator {

    public CheckedLog2(CommonExpression expression) {
        super(expression);
    }

    @Override
    protected String getOperator() {
        return "log2";
    }

    @Override
    protected int getPriority() {
        return 2;
    }

    @Override
    protected int operate(int x) {
        return IntegerOperation.safeLog(x, 2);
    }

    @Override
    protected boolean checkSpecialOperator() {
        return false;
    }
}
