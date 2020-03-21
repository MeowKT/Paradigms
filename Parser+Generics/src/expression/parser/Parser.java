package expression.parser;

import expression.exceptions.ComputationalException;
import expression.operators.GenericExpression;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public interface Parser {
    GenericExpression parse(String expression) throws ComputationalException;
}
