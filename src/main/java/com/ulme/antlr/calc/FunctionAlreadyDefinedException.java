package com.ulme.antlr.calc;

import org.antlr.v4.runtime.Token;

public class FunctionAlreadyDefinedException extends CompileException {
    private String methodNameToken;

    FunctionAlreadyDefinedException(Token methodNameToken, String functionName) {
        super(methodNameToken);
        this.methodNameToken = functionName;
    }

    @Override
    public String getMessage() {
        return getLine() + ":" + getColumn() + " function already defined: <" + methodNameToken + ">";
    }

}
