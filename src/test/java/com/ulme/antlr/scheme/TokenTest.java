package com.ulme.antlr.scheme;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonToken;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Token;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class TokenTest {

    @Test
    public void testTokens() throws Exception {
        // given
        String expression = "(list a)";

        // when
        ANTLRInputStream antlrInputStream = new ANTLRInputStream(expression);
        SchemeLexer calcLexer = new SchemeLexer(antlrInputStream);
        CommonTokenStream tokenStream = new CommonTokenStream(calcLexer);
        tokenStream.fill();

        // then
        List<Token> tokens = tokenStream.getTokens();
        assertEquals(5, tokens.size());
        assertEquals(SchemeLexer.OPEN_BRACE, tokens.get(0).getType());
        assertEquals(SchemeLexer.LIST, tokens.get(1).getType());
        assertEquals(SchemeLexer.IDENTIFIER, tokens.get(2).getType());
        assertEquals(SchemeLexer.CLOSE_BRACE, tokens.get(3).getType());
        assertEquals(SchemeLexer.EOF, tokens.get(4).getType());
    }
}
