package com.ulme.scheme.interpreter;

import org.junit.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class PrimitiveErrorTest {

    @Test
    public void throw_exception_if_variable_is_not_defined() {
        assertThatThrownBy(() -> {
                    SchemeEvaluator schemeEvaluator = new SchemeEvaluator(null);
                    schemeEvaluator.evaluateExpression("(display a)");
                }
        ).isInstanceOf(UndeclaredVariableException.class)
         .hasMessageContaining("1:9 undeclared variable <a>");
    }

    @Test
    public void throw_exception_if_variable_is_already_defined() {
        assertThatThrownBy(() -> {
                    SchemeEvaluator schemeEvaluator = new SchemeEvaluator(null);
                    schemeEvaluator.evaluateExpression("(define a 1)(define a 2)");
                }
        ).isInstanceOf(VariableAlreadyDefinedException.class)
         .hasMessageContaining("1:20 variable already defined: <a>");
    }
}
