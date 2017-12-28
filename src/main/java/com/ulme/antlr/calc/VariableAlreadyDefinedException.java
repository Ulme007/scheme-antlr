package com.ulme.antlr.calc;

import org.antlr.v4.runtime.Token;

public class VariableAlreadyDefinedException extends CompileException {

    private final String varName;

    VariableAlreadyDefinedException(Token varNameToken) {
        super(varNameToken);
        varName = varNameToken.getText();
    }

    @Override
    public String getMessage() {
        return getLine() + ":" + getColumn() + " variable already defined: <" + varName + ">";
    }
}
