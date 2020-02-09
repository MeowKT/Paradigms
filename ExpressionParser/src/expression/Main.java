package expression;

import expression.parser.ExpressionParser;

public class Main {
    public static void main(String[] args) {
        System.out.println("x ** 2");
        System.out.println(new ExpressionParser().parse("x * 2"));
    }
}
