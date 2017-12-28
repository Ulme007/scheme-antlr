package com.ulme.antlr.calc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class SchemePrimitiveTest {

    private String description;
    private String expression;
    private String result;

    public SchemePrimitiveTest(String description, String expression, String result) {
        this.description = description;
        this.expression = expression;
        this.result = result;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"Display positive number 1",   "(display 1)",  "1"},
                {"Display negative number -2",  "(display -2)", "-2"},
                {"1 + 2 = 3",               "(display (+ 1 2))",    "3"},
                {"1 + 2 + 3 = 6",           "(display (+ 1 2 3))",  "6"},
                {"2-1=-1",                  "(display (- 1 2))",    "-1"},
                {"2 * 3 = 6",               "(display (* 2 3))",    "6"},
                {"6 / 3 = 2",               "(display (/ 6 3))",    "2"},
                {"(1 + 2) * (3 + 4) = 21",  "(display (* (+ 1 2) (+ 3 4)))", "21"},
                {"(define a 1)",  "(define a 1)(display a)", "1"},
                {"Define two variables and add these",
                        "(define a 1)" +
                        "(define b 2)" +
                        "(display (+ a b))", "3"},
        });
    }

    @Test
    public void test() throws Exception {
        ByteArrayOutputStream bout = new ByteArrayOutputStream();

        try (PrintStream printStream = new PrintStream(bout)) {
            SchemeEvaluator schemeEvaluator = new SchemeEvaluator(printStream);
            schemeEvaluator.evaluateExpression(expression);
        }

        assertEquals(description, result, bout.toString());
    }
}