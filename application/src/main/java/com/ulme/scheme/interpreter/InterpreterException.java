package com.ulme.scheme.interpreter;

import org.antlr.v4.runtime.Token;

class InterpreterException extends RuntimeException {

    private final int line;
    private final int column;

    InterpreterException(Token token) {
        line = token.getLine();
        column = token.getCharPositionInLine();
    }

    int getLine() {
        return line;
    }

    int getColumn() {
        return column;
    }
}
