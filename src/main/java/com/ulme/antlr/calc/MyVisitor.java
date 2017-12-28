package com.ulme.antlr.calc;

import com.ulme.antlr.scheme.SchemeBaseVisitor;
import com.ulme.antlr.scheme.SchemeParser;

import java.io.PrintStream;
import java.util.List;

public class MyVisitor extends SchemeBaseVisitor<Long> {

    private PrintStream out;

    public MyVisitor(PrintStream out) {
        this.out = out;
    }

    @Override
    public Long visitProg(SchemeParser.ProgContext ctx) {
        System.out.println("visitProgramm");
        return super.visitProg(ctx);
    }

    @Override
    public Long visitDisplay(SchemeParser.DisplayContext ctx) {
        Long value = visit(ctx.expr());
        out.print(value);
        return null;
    }

    @Override
    public Long visitPlus(SchemeParser.PlusContext ctx) {
        List<SchemeParser.ExprContext> expr = ctx.expr();
        Long result = 0L;
        for (SchemeParser.ExprContext subCtx : expr) {
            result += visit(subCtx);
        }
        return result;
    }

    @Override
    public Long visitNumber(SchemeParser.NumberContext ctx) {
        return Long.parseLong(ctx.number.getText());
    }
}
