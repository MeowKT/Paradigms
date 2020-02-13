package expression.parser;

import java.util.Map;

public enum UnaryOperator {
    Pow2, Log2, Minus, Undefined;
    private static final Map<UnaryOperator, String> operatorToString = Map.of(
            Minus, "-",
            Pow2, "pow2",
            Log2, "log2"
    );

    @Override
    public String toString() {
        return operatorToString.get(this);
    }

}
