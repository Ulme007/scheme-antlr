package com.ulme.antlr.calc;

import com.ulme.antlr.scheme.SchemeLexer;
import com.ulme.antlr.scheme.SchemeParser;
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

    Long evaluateFile(String filename) throws IOException {
        ANTLRInputStream antlrInputStream = new ANTLRFileStream(filename);
        return evaluate(antlrInputStream);
    }

    Long evaluateExpression(String expression) {
        ANTLRInputStream antlrInputStream = new ANTLRInputStream(expression);
        return evaluate(antlrInputStream);
    }

    private Long evaluate(ANTLRInputStream antlrInputStream) {
        SchemeLexer calcLexer = new SchemeLexer(antlrInputStream);
        CommonTokenStream commonTokenStream = new CommonTokenStream(calcLexer);
        SchemeParser calcParser = new SchemeParser(commonTokenStream);

        ParseTree parseTree = calcParser.program();
        return new MyVisitor(printStream).visit(parseTree);
    }
}
