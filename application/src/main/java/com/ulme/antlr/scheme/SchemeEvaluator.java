package com.ulme.antlr.scheme;

import com.ulme.antlr.scheme.types.Type;
import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.IOException;
import java.io.PrintStream;

public class SchemeEvaluator {

    private PrintStream printStream;

    SchemeEvaluator(PrintStream printStream) {
        this.printStream = printStream;
    }

    Type evaluateFile(String filename) throws IOException {
        ANTLRInputStream antlrInputStream = new ANTLRFileStream(filename);
        return evaluate(antlrInputStream);
    }

    Type evaluateExpression(String expression) {
        ANTLRInputStream antlrInputStream = new ANTLRInputStream(expression);
        return evaluate(antlrInputStream);
    }

    private Type evaluate(ANTLRInputStream antlrInputStream) {
        SchemeLexer lexer = new SchemeLexer(antlrInputStream);
        CommonTokenStream tokenStream = new CommonTokenStream(lexer);
        SchemeParser parser = new SchemeParser(tokenStream);

        ParseTree parseTree = parser.program();
        return new SchemeTreeVisitor(printStream).visit(parseTree);
    }
}
