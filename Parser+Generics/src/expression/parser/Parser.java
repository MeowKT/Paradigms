package expression.parser;

import expression.operators.TripleExpression;
import expression.exceptions.ComputationalException;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public interface Parser <T> {
    TripleExpression<T> parse(String expression) throws ComputationalException;
}
