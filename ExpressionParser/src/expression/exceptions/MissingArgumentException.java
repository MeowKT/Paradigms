package expression.exceptions;

public class MissingArgumentException extends ParsingException {
    public MissingArgumentException(Character c) {
        super("Expected argument, actual: " + c);
    }
}
