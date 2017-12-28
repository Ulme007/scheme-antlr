package com.ulme.antlr.calc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class SchemeFunctionErrorTest {

    @Test
    public void throw_exception_if_variable_is_not_defined() {
        assertThatThrownBy(() -> {
                    SchemeEvaluator schemeEvaluator = new SchemeEvaluator(null);
                    schemeEvaluator.evaluateExpression(
                            "(sum (1 2))"
                    );
                }
        ).isInstanceOf(UndefinedFunctionException.class)
         .hasMessageContaining("1:1 call to undefined function: <sum(2)>");
    }

    @Test
    public void throw_exception_if_function_is_already_defined() {
        assertThatThrownBy(() -> {
                    SchemeEvaluator schemeEvaluator = new SchemeEvaluator(null);
                    schemeEvaluator.evaluateExpression(
                            "(define (sum x1 x2)(+ x1 x2))" +
                                    "(define (sum x1 x2)(+ x1 x2))"
                    );
                }
        ).isInstanceOf(FunctionAlreadyDefinedException.class)
         .hasMessageContaining("1:38 function already defined: <sum(2)>");
    }
}