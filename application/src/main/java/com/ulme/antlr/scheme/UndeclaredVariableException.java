package com.ulme.antlr.scheme;

import org.antlr.v4.runtime.Token;

public class UndeclaredVariableException extends CompileException {

    private String varName;

    UndeclaredVariableException(Token varNameToken) {
        super(varNameToken);
        varName = varNameToken.getText();
    }

    @Override
    public String getMessage() {
        return getLine() + ":" + getColumn() + " undeclared variable <" + varName + ">";
    }
}
