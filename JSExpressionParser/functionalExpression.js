"use strict";

const cnst = val => () => val;
const one = cnst(1);
const two = cnst(2);

const variable = name => (...args) => {
    return args[variables[name]];
};

const multiOperator = op => (...expressions) => (...values) => op(...expressions.map(expr => expr(...values)));

const add = (a, b) => multiOperator((a, b) => a + b)(a, b);
const divide = (a, b) => multiOperator((a, b) => a / b)(a, b);
const subtract = (a, b) => multiOperator((a, b) => a - b)(a, b);
const multiply = (a, b) => multiOperator((a, b) => a * b)(a, b);
const negate = (a) => multiOperator((a) => -a)(a);
const abs = (a) => multiOperator((a) => Math.abs(a))(a);
const iff = (a, b, c) => multiOperator((a, b, c) => a >= 0 ? b : c)(a, b, c);

const variables = {
    "x": 0,
    "y": 1,
    "z": 2
};

const operations = {
    "+": add,
    "-": subtract,
    "*": multiply,
    "/": divide,
    "negate": negate,
    "abs": abs,
    "iff": iff
};

const consts = {
    "one" : one,
    "two" : two
};

const parse = expression => {
    let stack = [];

    function parseElement(element) {
        if (element in operations) {
            stack.push(operations[element](...stack.splice(-(operations[element].length))));
        } else if (element in variables) {
            stack.push(variable(element));
        } else if (element in consts) {
            stack.push(consts[element])
        } else {
            stack.push(cnst(parseInt(element)));
        }
    }

    expression.trim().split(/\s+/).forEach(parseElement);
    return stack.pop();
};