package com.ulme.scheme.interpreter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class PrimitiveTest {

    private String description;
    private String expression;
    private String expectedResult;

    public PrimitiveTest(String description, String expression, String expectedResult) {
        this.description = description;
        this.expression = expression;
        this.expectedResult = expectedResult;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"Print a new line", "(newline)", "\n"},
                {"Display positive number 1", "(display 1)", "1"},
                {"Display negative number -2", "(display -2)", "-2"},
                {"(+ 1 2)", "(display (+ 1 2))", "3"},
                {"(+ 1 2 3)", "(display (+ 1 2 3))", "6"},
                {"(- 1 2)", "(display (- 1 2))", "-1"},
                {"(- 1 2 3)", "(display (- 1 2 3))", "-4"},
                {"(* 2 3)", "(display (* 2 3))", "6"},
                {"(* 2 3 4)", "(display (* 2 3 4))", "24"},
                {"(/ 6 3)", "(display (/ 6 3))", "2"},
                {"(/ 16 4 2)", "(display (/ 16 4 2))", "2"},
                {"(* (+ 1 2) (+ 3 4)))", "(display (* (+ 1 2) (+ 3 4)))", "21"},
                {"Define a variable", "(define a 1)(display a)", "1"},
                {"Define two variables and add these",
                        "(define a 1)" +
                                "(define b 2)" +
                                "(display (+ a b))", "3"},
        });
    }

    @Test
    public void test() throws Exception {
        SchemeEvaluator schemeEvaluator = new SchemeEvaluator();
        String result = schemeEvaluator.evaluateExpression(expression);

        assertEquals(description, expectedResult, result);
    }
}