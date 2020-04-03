"use strict";

function expressionInterface(constr, evaluate, toString, diff) {
    constr.prototype.evaluate = evaluate;
    constr.prototype.toString = toString;
    constr.prototype.diff = diff;
}

function Const(x) {
    this.x = x;
}
expressionInterface(
    Const,
    function() { return parseInt(this.x) },
    function() { return this.x.toString() },
    function() { return ZERO }
);

const ZERO = new Const(0);
const ONE = new Const(1);
const TWO = new Const(2);

function Variable(variable) {
    this.variable = variable;
    this.number = variables[variable];
}
expressionInterface(
    Variable,
    function(...vars) { return vars[this.number] },
    function() { return this.variable },
    function(variable) { return this.variable === variable ? ONE : ZERO }
);

const variables = {
    x: 0,
    y: 1,
    z: 2
};

function MultiExpression(...args) {
    this.args = args;
}
expressionInterface(
    MultiExpression,
    function(...elements) { return this.op(...this.args.map(el => el.evaluate(...elements))); },
    function() { return this.args.join(" ") + " " + this.symbol; },
    function(variable) { return this.diffRule(variable, ...this.args); }
);

function makeOperation(op, symbol, diffRule) {
    let operation = function (...args) {
        MultiExpression.call(this, ...args);
    };
    operation.prototype = Object.create(MultiExpression.prototype);
    operation.prototype.op = op;
    operation.prototype.symbol = symbol;
    operation.prototype.diffRule = diffRule;
    return operation;
}

const Negate = makeOperation(
    a => -a,
    "negate",
    (name, a) => new Negate(a.diff(name))
);

const Add = makeOperation(
    (a, b) => a + b,
    "+",
    (name, a, b) => new Add(a.diff(name), b.diff(name)));

const Subtract = makeOperation(
    (a, b) => a - b,
    "-",
    (name, a, b) => new Subtract(a.diff(name), b.diff(name)),
);

const Multiply = makeOperation(
    (a, b) => a * b,
    "*",
    (name, a, b) =>
        new Add(
            new Multiply(a, b.diff(name)),
            new Multiply(a.diff(name), b))
);

const Divide = makeOperation(
    (a, b) => a / b,
    "/",
    (name, a, b) =>
        new Divide(
            new Subtract(
                new Multiply(a.diff(name), b),
                new Multiply(a, b.diff(name))),
            new Multiply(b, b))
);

const Gauss = makeOperation(
    (a, b, c, x) => a * Math.exp(-(x - b) * (x - b) / (c * c * 2)),
    "gauss",
    function(name, a, b, c, x) {
        let sub = new Subtract(x, b);
        return new Multiply(
                    new Gauss(ONE, b, c, x),
                    new Add(
                        a.diff(name),
                        new Multiply(
                            a,
                            new Negate(
                                new Divide(
                                    new Multiply(
                                        sub,
                                        sub),
                                    new Multiply(
                                        TWO,
                                        new Multiply(c, c)))).diff(name))))
    }
);


const operations = {
    "gauss": [Gauss, 4],
    "+": [Add, 2],
    "-": [Subtract, 2],
    "*": [Multiply, 2],
    "/": [Divide, 2],
    "negate": [Negate, 1],
};

function parse(expression) {
    let stack = [];

    for (let token of expression.trim().split(/\s+/)) {
        if (token in operations) {
            let [operation, cntArgs] = operations[token];
            stack.push(new operation(...stack.splice(-cntArgs)));
        } else if (token in variables) {
            stack.push(new Variable(token));
        } else {
            stack.push(new Const(parseInt(token)));
        }
    }
    return stack.pop();
}