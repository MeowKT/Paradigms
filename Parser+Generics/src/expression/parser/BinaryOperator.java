package expression.parser;

import java.util.Map;

public enum BinaryOperator {
    Add, Sub, Mul, Div, UNDEFINED;
    private static final Map<BinaryOperator, String> operatorToString = Map.of(
            Add, "+",
            Sub, "-",
            Mul, "*",
            Div, "/"
    );

    private static final Map<BinaryOperator, Integer> OPERATOR_LVL = Map.of(
            UNDEFINED, 3,
            Add, 0,
            Sub, 0,
            Mul, 1,
            Div, 1
    );

    public int getLvl() {
        return OPERATOR_LVL.get(this);
    }

    @Override
    public String toString() {
        return operatorToString.get(this);
    }

}
