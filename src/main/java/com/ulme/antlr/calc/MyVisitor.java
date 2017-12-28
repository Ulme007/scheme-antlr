package com.ulme.antlr.calc;

import com.ulme.antlr.scheme.SchemeBaseVisitor;
import com.ulme.antlr.scheme.SchemeParser;

import java.io.PrintStream;

public class MyVisitor extends SchemeBaseVisitor<Long> {

    private PrintStream out;

    public MyVisitor(PrintStream out) {
        this.out = out;
    }

    @Override
    public Long visitProgramm(SchemeParser.ProgrammContext ctx) {
        System.out.println("visitProgramm");
        return super.visitProgramm(ctx);
    }

    @Override
    public Long visitPrint(SchemeParser.PrintContext ctx) {
        Long value = visit(ctx.expression());
        out.print(value);
        return null;
    }

    @Override
    public Long visitPlus(SchemeParser.PlusContext ctx) {
        return super.visitPlus(ctx);
    }

    @Override
    public Long visitNumber(SchemeParser.NumberContext ctx) {
        return Long.parseLong(ctx.number.getText());
    }
}
