package expression.exceptions;

public class UnsupportedOperatorException extends ParsingException {
    public UnsupportedOperatorException(String message) {
        super("Unsupported" + message);
    }
}
