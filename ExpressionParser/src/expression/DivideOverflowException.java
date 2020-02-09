package expression;

public class DivideOverflowException extends AbstractOperateException {
    public DivideOverflowException(int x, int y) {
        super("Divide", x + " / " + y);
    }
}
