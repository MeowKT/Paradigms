package expression.operators;

import expression.exceptions.ComputationalException;
import expression.parser.ExpressionParser;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws ComputationalException {
        System.out.println(new ExpressionParser().parse("3 ** (4 * 8)").evaluate(3,2,8));
    }
}
