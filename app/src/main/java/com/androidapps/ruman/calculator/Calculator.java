package com.androidapps.ruman.calculator;

/**
 * Created by A B M Ruman on 19/10/2015 for Project: Calculator.
 */

import com.fathzer.soft.javaluator.DoubleEvaluator;
import com.fathzer.soft.javaluator.Parameters;

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
    public Double calculate(String expression) {
        expression = formExpression(expression);
        return super.evaluate(expression);
    }

    String formExpression(String str) {
        //String expression;
        str = str.replace('x', '*');
        str = str.replace('÷', '/');
        str = str.replace("π", "pi");
        return str;
    }
}
