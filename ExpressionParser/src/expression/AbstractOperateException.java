package expression;

import expression.parser.ComputationalException;

public abstract class AbstractOperateException extends ComputationalException {
    public AbstractOperateException(String operator, String message) {
        super(operator + " operator overflow: " + message);
    }
}
