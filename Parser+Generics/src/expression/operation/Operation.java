package expression.operation;

import expression.exceptions.ComputationalException;
import expression.exceptions.IllegalConstantException;
import expression.exceptions.OverflowException;

public interface Operation <T> {
    T parseNumber(final String number);

    T add(final T x, final T y);

    T subtract(final T x, final T y);

    T multiply(final T x, final T y);

    T divide(final T x, final T y);

    T negate(final T x);

    T parse(final String x);
}
