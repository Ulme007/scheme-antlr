package com.ulme.antlr.calc;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class SchemeErrorTest {

    @Test
    public void throw_exception_if_variable_is_not_defined() {
        assertThatThrownBy(() ->
                {
                    ByteArrayOutputStream bout = new ByteArrayOutputStream();

                    try (PrintStream printStream = new PrintStream(bout)) {
                        SchemeEvaluator schemeEvaluator = new SchemeEvaluator(printStream);
                        schemeEvaluator.evaluateExpression("(display a)");
                    }
                }
        ).isInstanceOf(UndeclaredVariableException.class)
         .hasMessageContaining("1:9 undeclared variable <a>");
    }
}
