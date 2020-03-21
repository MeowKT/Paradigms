package expression.operators;

import expression.operation.Operation;

public class Variable implements GenericExpression {
    private String var;

    public Variable(String var) {
        this.var = var;
    }

    @Override
    public <T> T evaluate(T x, T y, T z, Operation<T> op) {
        switch (var) {
            case("x") : return x;
            case("y") : return y;
            default: return z;
        }

    }

    @Override
    public String toString() {
        return var;
    }

}
