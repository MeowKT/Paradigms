package expression.operators;

public class Const <T> implements TripleExpression<T> {
    private T val;

    public Const(T val) {
        this.val = val;
    }

    @Override
    public T evaluate(T x, T y, T z) {
        return val;
    }

    @Override
    public String toString() {
        return val.toString();
    }

}
