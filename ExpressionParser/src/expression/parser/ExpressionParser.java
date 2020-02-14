package expression.parser;

import expression.exceptions.*;
import expression.operators.*;

import java.util.Map;

public class ExpressionParser extends BaseParser implements Parser {

     private static Map<String, BinaryOperator> prefixToBinaryOperator = Map.of(
            "", BinaryOperator.Undefined,
            "+", BinaryOperator.Add,
            "-", BinaryOperator.Sub,
            "*", BinaryOperator.Mul,
            "/", BinaryOperator.Div,
            "**", BinaryOperator.Pow,
            "//", BinaryOperator.Log
     );

     private static Map<String, UnaryOperator> prefixToUnaryOperator = Map.of(
             "l", UnaryOperator.Log2,
             "p", UnaryOperator.Pow2,
             "-", UnaryOperator.Minus
     );

    private BinaryOperator lastBinaryOperator = BinaryOperator.Undefined;
    private UnaryOperator lastUnaryOperator = UnaryOperator.Undefined;
    private static final int MAX_LEVEL = 3;

    public CommonExpression parse(String expression) {
        setSource(new StringSource(expression));
        nextChar();
        CommonExpression result = parseExpression();
        if (test('\0')) {
            return result;
        }
        if (ch == ')') {
            throw new MissingBracketException("open", pos);
        }
        throw new UnknownSymbolException(ch + "", pos);
    }

    private CommonExpression parseExpression() {
        return parseLevel(0);
    }

    private CommonExpression parseLevel(int level) {
        if (level == MAX_LEVEL) {
            return parseSimpleExpression();
        }
        CommonExpression result = parseLevel(level + 1);
        while (hasBinaryOperationOnLevel(level)) {
            BinaryOperator op = lastBinaryOperator;
            lastBinaryOperator = BinaryOperator.Undefined;
            result = makeBinaryExpression(op, result, parseLevel(level + 1));
        }
        return result;
    }

    private boolean hasBinaryOperationOnLevel(int level) {
        skipWhitespace();
        if (lastBinaryOperator == BinaryOperator.Undefined) {
            StringBuilder st = new StringBuilder();
            st.append(ch);
            while (prefixToBinaryOperator.containsKey(st.toString())) {
                nextChar();
                st.append(ch);
            }
            st.setLength(st.length() - 1);
            lastBinaryOperator = prefixToBinaryOperator.get(st.toString());
        }
        return lastBinaryOperator != BinaryOperator.Undefined && lastBinaryOperator.getLvl() == level;
    }

    private boolean hasUnaryOperationOnLevel() {
        skipWhitespace();
        if (prefixToUnaryOperator.containsKey(ch + "")) {
            lastUnaryOperator = prefixToUnaryOperator.get(ch + "");
            expect(lastUnaryOperator.toString());
        }
        return lastUnaryOperator != UnaryOperator.Undefined;
    }

    private CommonExpression parseSimpleExpression() {
        skipWhitespace();
        if (test('(')) {
            CommonExpression result = parseExpression();
            skipWhitespace();
            if (ch != ')') {
                throw new MissingBracketException("close", pos);
            }
            nextChar();
            return result;

        } else if (between('0', '9')) {
            return parseConstExpression(false);
        } else if (hasUnaryOperationOnLevel()) {
            UnaryOperator op = lastUnaryOperator;
            lastUnaryOperator = UnaryOperator.Undefined;
            if (op == UnaryOperator.Minus) {
                if (between('0', '9')) {
                    return parseConstExpression(true);
                } else {
                    return new CheckedNegate(parseSimpleExpression());
                }
            } else {
                if (between('0', '9') || between('a', 'z')) {
                    throw new UnknownSymbolException(ch + "", pos);
                }
                return makeUnaryExpression(op, parseSimpleExpression());
            }
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
            throw new MissingArgumentException(ch, pos);
        }
        return new Variable(st.toString());
    }

    private CommonExpression makeBinaryExpression(BinaryOperator operator, CommonExpression a, CommonExpression b) {
        switch (operator) {
            case Add : return new CheckedAdd(a, b);
            case Sub : return new CheckedSubtract(a, b);
            case Mul : return new CheckedMultiply(a, b);
            case Div : return new CheckedDivide(a, b);
            case Pow : return new CheckedPow(a, b);
            case Log : return new CheckedLog(a, b);
        }
        throw new UnsupportedOperatorException("binary operator: " + operator, pos);
    }

    private CommonExpression makeUnaryExpression(UnaryOperator operator, CommonExpression a) {
        switch (operator) {
            case Log2: return new CheckedLog2(a);
            case Pow2: return new CheckedPow2(a);
        }
        throw new UnsupportedOperatorException("unary operator: " + operator, pos);
    }

    private void skipWhitespace() {
        while (test(' ') || test('\r') || test('\n') || test('\t')) {

        }
    }
}