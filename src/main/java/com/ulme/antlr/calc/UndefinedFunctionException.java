package com.ulme.antlr.calc;

import org.antlr.v4.runtime.Token;

public class UndefinedFunctionException extends CompileException {

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
