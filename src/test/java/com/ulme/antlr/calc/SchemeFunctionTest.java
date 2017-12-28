package com.ulme.antlr.calc;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@Ignore
@RunWith(Parameterized.class)
public class SchemeFunctionTest {

    private String description;
    private String expression;
    private String result;

    public SchemeFunctionTest(String description, String expression, String result) {
        this.description = description;
        this.expression = expression;
        this.result = result;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"Define and use sum function",
                        "(define (sum x1 x2)(+ x1 x2))(display (sum 2 3))",  "5"},
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