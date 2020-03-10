package expression.operators;

import java.util.Objects;

public class Variable<T> implements TripleExpression<T> {
    private String var;

    public Variable(String var) {
        this.var = var;
    }

    @Override
    public T evaluate(T x, T y, T z) {
        switch (var) {
            case("x") : return x;
            case("y") : return y;
            case("z") : return z;
            default: return z;
        }

    }

    @Override
    public String toString() {
        return var;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        Variable variable = (Variable) object;
        return Objects.equals(var, variable.var);
    }

    @Override
    public int hashCode() {
        return 313 * Objects.hash(var);
    }
}
