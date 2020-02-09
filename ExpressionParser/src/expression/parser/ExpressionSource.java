package expression.parser;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public interface ExpressionSource {
    boolean hasNext();
    char next();
    ParserException error(String message);
}
