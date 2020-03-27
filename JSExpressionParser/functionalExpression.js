"use strict";

const cnst = val => () => val;
const one = cnst(1);
const two = cnst(2);

const variable = name => {
    let number = variables[name];
    return (...args) => args[number];
};

const multiOperator = op => (...expressions) => (...values) => op(...expressions.map(expr => expr(...values)));

const associativeOperator = (f, val) => multiOperator((...args) => ([...args].reduce(f, val)));
const add = associativeOperator((a, b) => a + b, 0);
const multiply = associativeOperator((a, b) => a * b, 1);

const divide = multiOperator((a, b) => a / b);
const subtract = multiOperator((a, b) => a - b);
const negate = multiOperator((a) => -a);
const abs = multiOperator(Math.abs);
const iff = multiOperator((a, b, c) => a >= 0 ? b : c);

const variables = {
    "x": 0,
    "y": 1,
    "z": 2
};

const operations = {
    "+": [add, 2],
    "-": [subtract, 2],
    "*": [multiply, 2],
    "/": [divide, 2],
    "negate": [negate, 1],
    "abs": [abs, 1],
    "iff": [iff, 3]
};

const consts = {
    "one" : one,
    "two" : two
};

const parse = expression => {
    let stack = [];

    function parseElement(element) {
        if (element in operations) {
            let operation = operations[element][0];
            let cntArgs = operations[element][1];
            stack.push(operation(...stack.splice(-cntArgs)));
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