package com.ulme.antlr.scheme;

import org.junit.Ignore;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class ListTest {

    @Test
    public void createList() throws Exception {
        ByteArrayOutputStream bout = new ByteArrayOutputStream();

        try (PrintStream printStream = new PrintStream(bout)) {
            SchemeEvaluator schemeEvaluator = new SchemeEvaluator(printStream);
            schemeEvaluator.evaluateExpression("(display (list 1 2 3))");
        }

        assertEquals("(1 2 3)", bout.toString());
    }
}
