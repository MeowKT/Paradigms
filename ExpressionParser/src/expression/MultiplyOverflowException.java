package expression;

public class MultiplyOverflowException extends AbstractOperateException {
    public MultiplyOverflowException(int x, int y) {
        super("Multiply", x + " * " + y);
    }
}
