package com.ulme.scheme.interpreter;

import org.junit.Ignore;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class LengthTest {

    @Ignore
    @Test
    public void createList() throws Exception {
        ByteArrayOutputStream bout = new ByteArrayOutputStream();

        try (PrintStream printStream = new PrintStream(bout)) {
            SchemeEvaluator schemeEvaluator = new SchemeEvaluator(printStream);
            schemeEvaluator.evaluateExpression("(display (length (list 1 2 3)))");
        }

        assertEquals("3", bout.toString());
    }
}
