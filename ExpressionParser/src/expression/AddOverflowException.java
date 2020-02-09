package expression;

public class AddOverflowException extends AbstractOperateException {
    public AddOverflowException(int x, int y) {
        super("Add", x + " + " + y);
    }
}
