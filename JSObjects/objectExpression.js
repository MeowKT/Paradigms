"use strict";

function Const(x) {
    this.x = x;
}
Const.prototype.evaluate = function (expr) { return this.x; };
Const.prototype.toString = function () { return this.x.toString(); };
Const.prototype.diff = function (variable) { return new Const(0); };

const ONE = new Const(1);
const TWO = new Const(2);

const variables = {
    "x": 0,
    "y": 1,
    "z": 2
};

function Variable(variable) {
    this.variable = variable;
    this.number = variables[variable];
}

Variable.prototype.evaluate = function(...elements) { return elements[this.number]; };
Variable.prototype.toString = function() { return this.variable.toString(); };
Variable.prototype.diff = function(variable) { return new Const(variable === this.variable ? 1 : 0); };

function MultiExpression(op, symbol, diffRule, ...args) {
    this.args = args;
    this.op = op;
    this.symbol = symbol;
    this.diffRule = diffRule;
}
MultiExpression.prototype.toString = function() {
    return this.args.slice().reduce((sum, current) => sum + current.toString() + " ", "") + this.symbol;
};
MultiExpression.prototype.evaluate = function(...elements) {
    return this.op(...this.args.map(el => el.evaluate(...elements)));
};
MultiExpression.prototype.diff = function(variable) {
    return this.diffRule(variable, ...this.args);
};

function Negate(expr) {
    MultiExpression.call(this,
        a => -a,
        "negate",
        (name, a) =>
            new Negate(a.diff(name)),
        expr);
}
Negate.prototype = Object.create(MultiExpression.prototype);

function Add(left, right) {
    MultiExpression.call(this,
        (a, b) => a + b,
        "+",
        (name, a, b) =>
            new Add(a.diff(name), b.diff(name)),
        left, right);
}
Add.prototype = Object.create(MultiExpression.prototype);

function Subtract(left, right) {
    MultiExpression.call(this,
        (a, b) => a - b,
        "-",
        (name, a, b) =>
            new Subtract(a.diff(name), b.diff(name)),
        left, right);
}
Subtract.prototype = Object.create(MultiExpression.prototype);

function Multiply(left, right) {
    MultiExpression.call(this,
        (a, b) => a * b, "*",
        (name, a, b) =>
            new Add(
                new Multiply(a, b.diff(name)),
                new Multiply(a.diff(name), b)),
        left, right);
}
Multiply.prototype = Object.create(MultiExpression.prototype);

function Divide(left, right) {
    MultiExpression.call(this,
        (a, b) => a / b,
        "/",
        (name, a, b) =>
            new Divide(
                new Subtract(
                    new Multiply(a.diff(name), b),
                    new Multiply(a, b.diff(name))),
                new Multiply(b, b)),
        left, right);
}
Divide.prototype = Object.create(MultiExpression.prototype);

function Gauss(a, b, c, x) {
    MultiExpression.call(this,
        (a, b, c, x) => a * Math.exp(-(x - b) * (x - b) / (c * c * 2)),
        "gauss",
        (name, a, b, c, x) =>
            new Multiply(
                new Gauss(ONE, b, c, x),
                new Add(
                    a.diff(name),
                    new Multiply(
                        a,
                        new Negate(
                            new Divide(
                                new Multiply(
                                    new Subtract(x, b),
                                    new Subtract(x, b)),
                                new Multiply(
                                    TWO,
                                    new Multiply(c, c)))).diff(name)))),

        a, b, c, x);
}
Gauss.prototype = Object.create(MultiExpression.prototype);

const operations = {
    "gauss": [Gauss, 4],
    "+": [Add, 2],
    "-": [Subtract, 2],
    "*": [Multiply, 2],
    "/": [Divide, 2],
    "negate": [Negate, 1],
};

const parse = expression => {
    let stack = [];
    function parseElement(element) {
        if (element in operations) {
            let operation = operations[element][0];
            let cntArgs = operations[element][1];
            stack.push(new operation(...stack.splice(-cntArgs)));
        } else if (element in variables) {
            stack.push(new Variable(element));
        } else {
            stack.push(new Const(parseInt(element)));
        }
    }

    expression.trim().split(/\s+/).forEach(parseElement);
    return stack.pop();
};