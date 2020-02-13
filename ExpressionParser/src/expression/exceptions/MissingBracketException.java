package expression.exceptions;

public class MissingBracketException extends ParsingException {
    public MissingBracketException(String message) {
        super("Missing " + message + " bracket");
    }
}
