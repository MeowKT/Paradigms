"use strict";

const cnst = val => tmp => val;
const variable = name => x => x;
const binaryOperator = op => (l, r) => x => op(l(x), r(x));

const add = binaryOperator((a, b) => a + b);
const divide = binaryOperator((a, b) => a / b);
const subtract = binaryOperator((a, b) => a - b);
const multiply = binaryOperator((a, b) => a * b);

const stringToBinaryOperation = {
    "+": add,
    "-": subtract,
    "*": multiply,
    "/": divide,
};

const parse = expression => {
    let stack = [];

    function makeBinaryExpression(op) {
        let r = stack.pop(), l = stack.pop();
        stack.push(op(l, r));
    }

    const makeUnaryExpression = op => stack.push(op);

    function parseToken(element) {
        if (stringToBinaryOperation[element] !== undefined) {
            makeBinaryExpression(stringToBinaryOperation[element], stack);
        } else if (element === "x") {
            makeUnaryExpression(variable("x"), stack);
        } else {
            makeUnaryExpression(cnst(parseInt(element)), stack);
        }
    }

    expression.trim().split(/\s+/).forEach(parseToken);
    return stack.pop();
};