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
public class SchemeEvaluatorTest {

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"Print 1", "(print (1))", "1"},
                {"Print 2", "(print (2))", "2"},
//                {"Print 1+2", "(print (+ 1 2))", "2"},
        });
    }

    private String description;
    private String expression;
    private String result;

    public SchemeEvaluatorTest(String description, String expression, String result) {
        this.description = description;
        this.expression = expression;
        this.result = result;
    }

    @Test
    public void test() throws Exception {
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(bout);
        SchemeEvaluator schemeEvaluator = new SchemeEvaluator(printStream);
        schemeEvaluator.evaluateExpression(expression);

        assertEquals(description, result, bout.toString());
    }
}