package com.androidapps.ruman.calculator;

/**
 * Created by A B M Ruman on 19/10/2015 for Project: Calculator.
 */

import com.fathzer.soft.javaluator.Constant;
import com.fathzer.soft.javaluator.DoubleEvaluator;
import com.fathzer.soft.javaluator.Function;
import com.fathzer.soft.javaluator.Operator;
import com.fathzer.soft.javaluator.Parameters;

import java.util.Iterator;

public class Calculator extends DoubleEvaluator {
    public Calculator() {
        super();
    }

    public Calculator(Parameters parameters) {
        super(parameters);
    }

    @Override
    protected Double toValue(String literal, Object evaluationContext) {
        literal = formExpression(literal);
        return super.toValue(literal, evaluationContext);
    }

    protected Double calculate(Constant constant, Object evaluationContext) {
        String expression = formExpression(constant.toString());
        return super.evaluate(expression, evaluationContext);
    }

    protected Double calculate(Operator operator, Iterator<Double> operands, Object evaluationContext) {
        return super.evaluate(operator, operands, evaluationContext);
    }

    protected Double calculate(Function function, Iterator<Double> arguments, Object evaluationContext) {
        return super.evaluate(function, arguments, evaluationContext);
    }

    public Double calculate(String expression) {
        expression = formExpression(expression);
        return super.evaluate(expression);
    }

    public Double calculate(String expression, Object evaluationContext) {
        expression = formExpression(expression);
        return super.evaluate(expression, evaluationContext);
    }

    String formExpression(String str) {
        //String expression;
        str = str.replace('x', '*');
        str = str.replace('÷', '/');
        str = str.replace("π", "pi");
        return str;
    }
}
