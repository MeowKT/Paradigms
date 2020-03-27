package expression.operation;


public class DoubleOperation implements Operation<Double> {

    @Override
    public Double add(Double x, Double y) {
        return x + y;
    }

    @Override
    public Double subtract(Double x, Double y) {
        return x - y;
    }

    @Override
    public Double multiply(Double x, Double y) {
        return x * y;
    }

    @Override
    public Double divide(Double x, Double y) {
        return x / y;
    }

    @Override
    public Double negate(Double x) {
        return -x;
    }

    @Override
    public Double parse(String x) {
        return Double.parseDouble(x);
    }

    @Override
    public Double parse(int x) {
        return (double) x;
    }

    @Override
    public Double min(Double x, Double y) {
        return Double.min(x, y);
    }

    @Override
    public Double max(Double x, Double y) {
        return Double.max(x, y);
    }

    @Override
    public Double count(Double x) {
        return (double)Long.bitCount(Double.doubleToLongBits(x));
    }
}
