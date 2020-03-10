package expression.parser;

import java.util.Map;

public enum UnaryOperator {
    Minus, Undefined;
    private static final Map<UnaryOperator, String> operatorToString = Map.of(
            Undefined, "",
            Minus, "-"
    );

    @Override
    public String toString() {
        return operatorToString.get(this);
    }

}
