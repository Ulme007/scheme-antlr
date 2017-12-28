package com.ulme.antlr.calc;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        SchemeEvaluator schemeEvaluator = new SchemeEvaluator(System.out);
        long result = schemeEvaluator.evaluateExpression("3+42+5+6");
        System.out.println("Result: " + result);
    }
}
