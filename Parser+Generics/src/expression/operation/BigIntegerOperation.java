package expression.operation;

import expression.exceptions.IllegalOperationException;

import java.math.BigInteger;

public class BigIntegerOperation implements Operation<BigInteger> {

    @Override
    public BigInteger parseNumber(String number) {
        return new BigInteger(number);
    }

    @Override
    public BigInteger add(BigInteger x, BigInteger y) {
        return x.add(y);
    }

    @Override
    public BigInteger subtract(BigInteger x, BigInteger y) {
        return x.subtract(y);
    }

    @Override
    public BigInteger multiply(BigInteger x, BigInteger y) {
        return x.multiply(y);
    }

    @Override
    public BigInteger divide(BigInteger x, BigInteger y) {
        if (y.equals(BigInteger.ZERO)) {
            throw new IllegalOperationException("Divide by zero: ");
        }
        return x.divide(y);
    }

    @Override
    public BigInteger negate(BigInteger x) {
        return x.negate();
    }

    @Override
    public BigInteger parse(String x) {
        return new BigInteger(x);
    }
}
