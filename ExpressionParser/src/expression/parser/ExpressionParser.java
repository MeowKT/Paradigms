package expression.parser;

import expression.*;

import java.util.List;
import java.util.Map;

public class ExpressionParser extends BaseParser implements Parser {

    private static final int MAX_LEVEL = 2;
    private String lastOperator = "";

    private static List<String>[]  levels = new List[]{
            List.of("+", "-"),
            List.of("/", "*"),
      //      List.of("**")
    };

    public CommonExpression parse(String expression) {
        setSource(new StringSource(expression));
        nextChar();
        CommonExpression result = parseExpression();
        if (test('\0')) {
            return result;
        }
        throw error("Unknown character: " + ch);
    }

    private CommonExpression parseExpression() {
        return parseLevel(0);
    }

    private CommonExpression parseLevel(int level) {
        if (level == MAX_LEVEL) {
            return parseSimpleExpression();
        }
        CommonExpression result = parseLevel(level + 1);
        while (hasLevelOperation(level)) {
            result = makeBinaryExpression(lastOperator, result, parseLevel(level + 1));
        }
        return result;
    }

    private boolean hasLevelOperation(int level) {
        skipWhitespace();
        for (String s : levels[level]) {
            if (test(s.charAt(0))) {
                lastOperator = s;
                expect(lastOperator.substring(1));
                return true;
            }
        }
        return false;
    }

    private CommonExpression parseSimpleExpression() {
        skipWhitespace();
        if (test('(')) {
            CommonExpression result = parseExpression();
            skipWhitespace();
            if (ch != ')') {
                throw error("No closing bracket");
            }
            nextChar();
            return result;
        } else if (test('-')) {
            skipWhitespace();
            if (between('0', '9')) {
                return parseConstExpression(true);
            } else {
                return new CheckedNegate(parseSimpleExpression());
            }
        } else if (between('0', '9')) {
            return parseConstExpression(false);
        } else {
            return parseVariableExpression();
        }
    }
    private CommonExpression parseConstExpression(boolean isNegative) {
        StringBuilder st = new StringBuilder(isNegative ? "-" : "");
        while (between('0', '9')) {
            st.append(ch);
            nextChar();
        }
        try {
            return new Const(Integer.parseInt(st.toString()));
        } catch (NumberFormatException e) {
            throw error("Constant overflow: " + st);
        }
    }
    private CommonExpression parseVariableExpression() {
        StringBuilder st = new StringBuilder();
        while(between('x', 'z')) {
            st.append(ch);
            nextChar();
        }
        if (st.length() == 0) {
            throw error("No argument");
        }
        return new Variable(st.toString());
    }

    private CommonExpression makeBinaryExpression(String operator, CommonExpression a, CommonExpression b) {
        switch (operator) {
            case ("+") : return new CheckedAdd(a, b);
            case ("-") : return new CheckedSubtract(a, b);
            case ("*") : return new CheckedMultiply(a, b);
            case ("/") : return new CheckedDivide(a, b);
        }
        throw error("Unsupported operator: " + operator);
    }

    private void skipWhitespace() {
        while (test(' ') || test('\r') || test('\n') || test('\t')) {

        }
    }
}