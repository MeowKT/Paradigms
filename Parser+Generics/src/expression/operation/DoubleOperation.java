package expression.operation;


public class DoubleOperation implements Operation<Double> {

    @Override
    public Double parseNumber(String number) {
        return Double.parseDouble(number);
    }

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
}
