package expression.operators;

import expression.exceptions.ComputationalException;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public interface TripleExpression extends ToMiniString {
    int evaluate(int x, int y, int z) throws ComputationalException;
}