package expression;

public class SubtractOverflowException extends AbstractOperateException {
    public SubtractOverflowException(int x, int y) {
        super("Subtract", x + " - " + y);
    }
}
