package expression;

public class NegateOverflowException extends AbstractOperateException {
    public NegateOverflowException(int x) {
        super("Negate", "- " + x);
    }
}
