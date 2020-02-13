package expression.parser;

import expression.operators.CommonExpression;
import expression.exceptions.ComputationalException;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public interface Parser {
    CommonExpression parse(String expression) throws ComputationalException;
}
