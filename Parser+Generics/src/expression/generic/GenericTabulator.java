package expression.generic;

import expression.exceptions.ComputationalException;
import expression.operation.*;
import expression.operators.*;
import expression.parser.ExpressionParser;

import java.util.Map;

public class GenericTabulator implements Tabulator {
    private static final Map<String, Operation> MODES = Map.of(
            "i", new IntegerOperation(true),
            "d", new DoubleOperation(),
            "bi", new BigIntegerOperation(),
            "u", new IntegerOperation(false),
            "f", new FloatOperation(),
            "b", new ByteOperation()
    );

    @Override
    public Object[][][] tabulate(String mode, String expression, int x1, int x2, int y1, int y2, int z1, int z2) {
        return makeTable(getOperation(mode), expression, x1, x2, y1, y2, z1, z2);
    }

    private Operation<?> getOperation(final String mode) {
        if (!MODES.containsKey(mode)) {
            throw new ComputationalException("No mode " + mode);
        }
        return MODES.get(mode);
    }

    private <T> Object[][][] makeTable(Operation<T> op, String expression, int x1, int x2, int y1, int y2, int z1, int z2) {
        ExpressionParser parser = new ExpressionParser(op);
        GenericExpression commonExpression = parser.parse(expression);
        Object[][][] ans = new Object[x2 - x1 + 1][y2 - y1 + 1][z2 - z1 + 1];
        for (int x = x1; x <= x2; x++) {
            for (int y = y1; y <= y2; y++) {
                for (int z = z1; z <= z2; z++) {
                    try {
                        ans[x - x1][y - y1][z - z1] = commonExpression.evaluate(
                                op.parse(Integer.toString(x)),
                                op.parse(Integer.toString(y)),
                                op.parse(Integer.toString(z)),
                                op);
                    } catch (ComputationalException e) {
                        ans[x - x1][y - y1][z - z1] = null;
                    }
                }
            }
        }
        return ans;
    }
}