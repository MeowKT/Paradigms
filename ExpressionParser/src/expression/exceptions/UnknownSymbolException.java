package expression.exceptions;

public class UnknownSymbolException extends ComputationalException {
    public UnknownSymbolException(String message) {
        super("Unknown symbol: " + message);
    }
}
