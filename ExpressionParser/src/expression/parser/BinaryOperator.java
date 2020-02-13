package expression.parser;

import java.util.Map;

public enum BinaryOperator {
    Add, Sub, Mul, Div, Pow, Undefined, Log, Pow2, Log2;
    private static final Map<BinaryOperator, String> operatorToString = Map.of(
            Add, "+",
            Sub, "-",
            Mul, "*",
            Div, "/",
            Pow, "**",
            Log, "//"
    );

    private static final Map<BinaryOperator, Integer> OPERATOR_LVL = Map.of(
            Undefined, 3,
            Add, 0,
            Sub, 0,
            Mul, 1,
            Div, 1,
            Pow, 2,
            Log, 2
    );

    public int getLvl() {
        return OPERATOR_LVL.get(this);
    }

    @Override
    public String toString() {
        return operatorToString.get(this);
    }

}
