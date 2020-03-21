package expression.operators;

import expression.operation.Operation;

public class Const implements GenericExpression {
    private String val;

    public Const(String val, Operation op) {
        this.val = val;
    }

    @Override
    public <T> T evaluate(T x, T y, T z, Operation<T> op) {
        return op.parse(val);
    }

    @Override
    public String toString() {
        return val;
    }

}
