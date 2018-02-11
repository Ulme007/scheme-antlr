package com.ulme.scheme.interpreter;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CommentTest {

    @Test
    public void createList() throws Exception {
        SchemeEvaluator schemeEvaluator = new SchemeEvaluator();
        String result = schemeEvaluator.evaluateExpression("(display 1); This is a comment and will be skipped");

        assertEquals("1", result);
    }
}
