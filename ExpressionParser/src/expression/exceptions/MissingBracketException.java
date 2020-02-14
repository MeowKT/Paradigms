package expression.exceptions;

public class MissingBracketException extends ParsingException {
    public MissingBracketException(String message, int pos) {
        super("Missing " + message + " bracket at pos " + pos);
    }
}
