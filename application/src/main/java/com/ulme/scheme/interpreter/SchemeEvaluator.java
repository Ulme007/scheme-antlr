package com.ulme.scheme.interpreter;

import com.ulme.scheme.interpreter.types.Type;
import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

public class SchemeEvaluator {

    private PrintStream printStream;

    private SchemeEvaluator(PrintStream printStream) {
        this.printStream = printStream;
    }

    SchemeEvaluator() {
        this(System.out);
    }

    Type evaluateFile(String filename) throws IOException {
        ANTLRInputStream antlrInputStream = new ANTLRFileStream(filename);
        return evaluate(antlrInputStream);
    }

    String evaluateExpression(String expression) {
        ByteArrayOutputStream bout = new ByteArrayOutputStream();

        try (PrintStream printStream = new PrintStream(bout)) {
            SchemeEvaluator schemeEvaluator = new SchemeEvaluator(printStream);
            schemeEvaluator.evaluateExpressionGetType(expression);
        }

        return bout.toString();
    }

    private Type evaluateExpressionGetType(String expression) {
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
