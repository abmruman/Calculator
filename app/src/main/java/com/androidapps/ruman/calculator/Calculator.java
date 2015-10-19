package com.androidapps.ruman.calculator;

/**
 * Created by A B M Ruman on 19/10/2015.
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

    @Override
    protected Double evaluate(Constant constant, Object evaluationContext) {
        String expression = formExpression(constant.toString());
        return super.evaluate(expression, evaluationContext);
    }

    @Override
    protected Double evaluate(Operator operator, Iterator<Double> operands, Object evaluationContext) {
        return super.evaluate(operator, operands, evaluationContext);
    }

    @Override
    protected Double evaluate(Function function, Iterator<Double> arguments, Object evaluationContext) {
        return super.evaluate(function, arguments, evaluationContext);
    }

    @Override
    public Double evaluate(String expression) {
        expression = formExpression(expression);
        return super.evaluate(expression);
    }

    @Override
    public Double evaluate(String expression, Object evaluationContext) {
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
