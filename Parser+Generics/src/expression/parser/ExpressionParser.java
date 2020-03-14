package expression.parser;

import expression.exceptions.*;
import expression.operation.Operation;
import expression.operators.*;

import java.util.Map;

public class ExpressionParser<T> extends BaseParser {

    private final Operation<T> modeOperator;

     private static Map<String, BinaryOperator> prefixToBinaryOperator = Map.of(
            "", BinaryOperator.UNDEFINED,
            "+", BinaryOperator.Add,
            "-", BinaryOperator.Sub,
            "*", BinaryOperator.Mul,
            "/", BinaryOperator.Div,
             "m", BinaryOperator.Min,
             "ma", BinaryOperator.Max
     );

     private static Map<String, UnaryOperator> prefixToUnaryOperator = Map.of(
             "", UnaryOperator.Undefined,
             "-", UnaryOperator.Minus,
             "c", UnaryOperator.BitCount
     );

    private BinaryOperator lastBinaryOperator = BinaryOperator.UNDEFINED;
    private UnaryOperator lastUnaryOperator = UnaryOperator.Undefined;
    private static final int MAX_LEVEL = 3;

    public ExpressionParser(Operation<T> modeOperator) {
        this.modeOperator = modeOperator;
    }

    public TripleExpression<T> parse(String expression) {
        setSource(new StringSource(expression));
        nextChar();
        TripleExpression<T> result = parseExpression();
        if (test('\0')) {
            return result;
        }
        throw new UnknownSymbolException(ch + "", pos);
    }

    private TripleExpression<T> parseExpression() {
        return parseLevel(0);
    }

    private TripleExpression<T> parseLevel(int level) {
        if (level == MAX_LEVEL) {
            return parseSimpleExpression();
        }
        TripleExpression<T> result = parseLevel(level + 1);
        while (hasBinaryOperationOnLevel(level)) {
            BinaryOperator op = lastBinaryOperator;
            lastBinaryOperator = BinaryOperator.UNDEFINED;
            result = makeBinaryExpression(op, result, parseLevel(level + 1));
        }
        //System.out.println(result);
        return result;
    }

    private boolean hasBinaryOperationOnLevel(int level) {
        skipWhitespace();
        if (lastBinaryOperator == BinaryOperator.UNDEFINED) {
            StringBuilder st = new StringBuilder();
            st.append(ch);
            while (prefixToBinaryOperator.containsKey(st.toString())) {
                nextChar();
                st.append(ch);
            }
            st.setLength(st.length() - 1);
            lastBinaryOperator = prefixToBinaryOperator.get(st.toString());
            if (st.length() > 0) {
                expect(lastBinaryOperator.toString().substring(st.length()));
            }
        }
        return lastBinaryOperator != BinaryOperator.UNDEFINED && lastBinaryOperator.getLvl() == level;
    }

    private boolean hasUnaryOperation() {
        skipWhitespace();
        if (lastUnaryOperator == UnaryOperator.Undefined) {
            StringBuilder st = new StringBuilder();
            st.append(ch);
            while (prefixToUnaryOperator.containsKey(st.toString())) {
                nextChar();
                st.append(ch);
            }
            st.setLength(st.length() - 1);
            lastUnaryOperator = prefixToUnaryOperator.get(st.toString());
            if (st.length() > 0) {
                expect(lastUnaryOperator.toString().substring(st.length()));
            }
        }
        return lastUnaryOperator != UnaryOperator.Undefined;
    }

    private TripleExpression<T> parseSimpleExpression() {
        skipWhitespace();
        if (test('(')) {
            TripleExpression<T> result = parseExpression();
            skipWhitespace();
            if (ch != ')') {
                throw new MissingBracketException("close", pos);
            }
            nextChar();
            return result;
        } else if (between('0', '9')) {
            return parseConstExpression(false);
        } else if (hasUnaryOperation()) {
            UnaryOperator op = lastUnaryOperator;
            lastUnaryOperator = UnaryOperator.Undefined;
            if (between('0', '9')) {
                return parseConstExpression(true);
            } else if (op == UnaryOperator.Minus){
                return new Negate<T>(parseSimpleExpression(), modeOperator);
            }
            return makeUnaryExpression(op, parseSimpleExpression());
        } else {
            return parseVariableExpression();
        }
    }
    private TripleExpression<T> parseConstExpression(boolean isNegative) {
        StringBuilder st = new StringBuilder(isNegative ? "-" : "");
        while (between('0', '9')) {
            st.append(ch);
            nextChar();
        }
        try {
            return new Const<T>(modeOperator.parse(st.toString()));
        } catch (NumberFormatException e) {
            throw new IllegalConstantException("Constant overflow: " + st, pos);
        }
    }
    private TripleExpression<T> parseVariableExpression() {
        StringBuilder st = new StringBuilder();
        while (between('x', 'z')) {
            st.append(ch);
            nextChar();
        }
        if (st.length() == 0) {
            throw new MissingArgumentException(ch, pos);
        }
        return new Variable<T>(st.toString());
    }

    private TripleExpression<T> makeUnaryExpression(UnaryOperator operator, TripleExpression<T> a) {
        switch (operator) {
            case BitCount : return new Count<>(a, modeOperator);
            default: throw new UnsupportedOperatorException("unary operator: " + operator, pos);
        }
    }

    private TripleExpression<T> makeBinaryExpression(BinaryOperator operator, TripleExpression<T> a, TripleExpression<T> b) {
        switch (operator) {
            case Add : return new Add<>(a, b, modeOperator);
            case Sub : return new Subtract<>(a, b, modeOperator);
            case Mul : return new Multiply<>(a, b, modeOperator);
            case Div : return new Divide<>(a, b, modeOperator);
            case Min : return new Min<>(a, b, modeOperator);
            case Max : return new Max<>(a, b, modeOperator);
            default: throw new UnsupportedOperatorException("binary operator: " + operator, pos);
        }
    }

    private void skipWhitespace() {
        while (test(' ') || test('\r') || test('\n') || test('\t')) {

        }
    }
}