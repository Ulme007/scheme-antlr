package com.ulme.scheme.interpreter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class FunctionTest {

    private String description;
    private String expression;
    private String expectedResult;

    public FunctionTest(String description, String expression, String expectedResult) {
        this.description = description;
        this.expression = expression;
        this.expectedResult = expectedResult;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                //                {"Define and use function without parameter",
                //                        "(define (getOne)(1))(display (getOne))", "1"},
                //                {"Define and use identity function",
                //                        "(define (identity x)(x))(display (identity 2))", "2"},
                {"Define and use sum function",
                        "(define (sum x1 x2)(+ x1 x2))(display (sum 2 3))", "5"},
                //                {"Function with operator as argument",
                //                        "(define (h op x y)" +
                //                                "(op x y))" +
                //                                "(display(h + 23 42))" +
                //                                "(display(h * 23 42))", "5"},
        });
    }

    @Test
    public void test() throws Exception {
        SchemeEvaluator schemeEvaluator = new SchemeEvaluator();
        String result = schemeEvaluator.evaluateExpression(expression);

        assertEquals(description, expectedResult, result);
    }
}