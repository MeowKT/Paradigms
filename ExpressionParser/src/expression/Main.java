package expression;

import expression.exceptions.ComputationalException;
import expression.parser.ExpressionParser;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws ComputationalException {
        System.out.println(new ExpressionParser().parse("0**3").evaluate(3,2,8));
    }
}
