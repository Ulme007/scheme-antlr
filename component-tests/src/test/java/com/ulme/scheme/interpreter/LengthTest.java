package com.ulme.scheme.interpreter;

import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LengthTest {

    @Ignore
    @Test
    public void createList() throws Exception {
        SchemeEvaluator schemeEvaluator = new SchemeEvaluator();
        String result = schemeEvaluator.evaluateExpression("(display (length (list 1 2 3)))");
        assertEquals("3", result);
    }
}
