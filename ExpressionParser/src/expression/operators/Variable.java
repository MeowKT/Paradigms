package expression.operators;

import java.util.Objects;

public class Variable implements CommonExpression {
    private String var;

    public Variable(String var) {
        this.var = var;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        switch (var) {
            case("x") : return x;
            case("y") : return y;
            case("z") : return z;
        }
        return -1;
    }

    @Override
    public String toString() {
        return var;
    }

    @Override
    public String toMiniString() {
        return this.toString();
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
