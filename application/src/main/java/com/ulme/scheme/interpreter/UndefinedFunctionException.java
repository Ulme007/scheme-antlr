package com.ulme.scheme.interpreter;

import org.antlr.v4.runtime.Token;

public class UndefinedFunctionException extends InterpreterException {

    private String functionName;

    UndefinedFunctionException(Token functionNameToken, String functionName) {
        super(functionNameToken);
        this.functionName = functionName;
    }

    @Override
    public String getMessage() {
        return getLine() + ":" + getColumn() + " call to undefined function: <" + functionName + ">";
    }
}
