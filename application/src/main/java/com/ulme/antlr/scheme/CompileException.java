package com.ulme.antlr.scheme;

import org.antlr.v4.runtime.Token;

class CompileException extends RuntimeException {

    private final int line;
    private final int column;

    CompileException(Token token) {
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
