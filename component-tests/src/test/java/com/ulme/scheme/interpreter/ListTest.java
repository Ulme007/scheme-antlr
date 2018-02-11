package com.ulme.scheme.interpreter;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ListTest {

    @Test
    public void createList() throws Exception {
        SchemeEvaluator schemeEvaluator = new SchemeEvaluator();
        String result = schemeEvaluator.evaluateExpression("(display (list 1 2 3))");

        assertEquals("(1 2 3)", result);
    }
}
