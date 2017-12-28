package com.ulme.antlr.calc;

import com.ulme.antlr.scheme.SchemeBaseVisitor;
import com.ulme.antlr.scheme.SchemeParser;

import java.io.PrintStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyVisitor extends SchemeBaseVisitor<Long> {

    private PrintStream out;
    private Map<String, Long> env = new HashMap<>();

    MyVisitor(PrintStream out) {
        this.out = out;
    }

    @Override
    public Long visitIdentifier(SchemeParser.IdentifierContext ctx) {
        String varName = ctx.varName.getText();
        Long varValue = env.get(varName);
        return varValue;
    }

    @Override
    public Long visitDefine(SchemeParser.DefineContext ctx) {
        String varName = ctx.varName.getText();
        Long varValue = visit(ctx.expr());
        env.put(varName, varValue);
        return null;
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
    public Long visitMult(SchemeParser.MultContext ctx) {
        Long result = 0L;
        List<SchemeParser.ExprContext> expr = ctx.expr();
        if (expr.size() > 0) {
            result = visit(expr.get(0));
            for (int i = 1; i < expr.size(); i++) {
                result *= visit(expr.get(i));
            }
        }
        return result;
    }

    @Override
    public Long visitDiv(SchemeParser.DivContext ctx) {
        Long result = 0L;
        List<SchemeParser.ExprContext> expr = ctx.expr();
        if (expr.size() > 0) {
            result = visit(expr.get(0));
            for (int i = 1; i < expr.size(); i++) {
                result /= visit(expr.get(i));
            }
        }
        return result;
    }

    @Override
    public Long visitPlus(SchemeParser.PlusContext ctx) {
        Long result = 0L;
        List<SchemeParser.ExprContext> expr = ctx.expr();
        if (expr.size() > 0) {
            result = visit(expr.get(0));
            for (int i = 1; i < expr.size(); i++) {
                result += visit(expr.get(i));
            }
        }
        return result;
    }

    @Override
    public Long visitMinus(SchemeParser.MinusContext ctx) {
        Long result = 0L;
        List<SchemeParser.ExprContext> expr = ctx.expr();
        if (expr.size() > 0) {
            result = visit(expr.get(0));
            for (int i = 1; i < expr.size(); i++) {
                result -= visit(expr.get(i));
            }
        }
        return result;
    }

    @Override
    public Long visitNumber(SchemeParser.NumberContext ctx) {
        return Long.parseLong(ctx.number.getText());
    }
}
