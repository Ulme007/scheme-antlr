package com.ulme.antlr.calc;

import com.ulme.antlr.scheme.SchemeBaseVisitor;
import com.ulme.antlr.scheme.SchemeParser;
import org.antlr.v4.runtime.Token;

import java.io.PrintStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;

public class MyVisitor extends SchemeBaseVisitor<Long> {

    private PrintStream out;
    private Map<String, Long> env = new HashMap<>();
    private Map<String, SchemeParser.FunctionDefinitionContext> functions = new HashMap<>();

    MyVisitor(PrintStream out) {
        this.out = out;
    }

    @Override
    public Long visitFunctionCall(SchemeParser.FunctionCallContext ctx) {
        String functionName = getFunctionName(ctx.funcName.getText(), ctx.arguments.size());
        SchemeParser.FunctionDefinitionContext functionDefinitionContext = functions.get(functionName);
        if (functionDefinitionContext == null) {
            throw new UndefinedFunctionException(ctx.funcName, functionName);
        }

        //save global variables map
        Map<String, Long> oldEnv = env;

        // create a local variables map
        env = new HashMap<>();

        // set variables from function call

        List<SchemeParser.ExpressionContext> expressions = ctx.arguments;
        List<Token> declarations = functionDefinitionContext.paramNames;
        for (int i = 0; i < declarations.size(); i++) {
            String variableName = declarations.get(i).getText();
            Long value = visit(expressions.get(i));
            env.put(variableName, value);
        }

        Long result = visit(functionDefinitionContext.statements);

        // set back global variables map
        env = oldEnv;

        return result;
    }

    @Override
    public Long visitFunctionDefinition(SchemeParser.FunctionDefinitionContext ctx) {
        String functionName = getFunctionName(ctx.funcName.getText(), ctx.paramNames.size());
        if (functions.containsKey(functionName)) {
            throw new FunctionAlreadyDefinedException(ctx.funcName, functionName);
        }
        functions.put(functionName, ctx);

        return null;
    }

    @Override
    public Long visitIdentifier(SchemeParser.IdentifierContext ctx) {
        String varName = ctx.varName.getText();
        if (!env.containsKey(varName)) {
            throw new UndeclaredVariableException(ctx.varName);
        }
        return env.get(varName);
    }

    @Override
    public Long visitVariableDefinition(SchemeParser.VariableDefinitionContext ctx) {
        String varName = ctx.varName.getText();
        if (env.containsKey(varName)) {
            throw new VariableAlreadyDefinedException(ctx.varName);
        }
        Long varValue = visit(ctx.expression());
        env.put(varName, varValue);
        return null;
    }

    @Override
    public Long visitProgram(SchemeParser.ProgramContext ctx) {
        return super.visitProgram(ctx);
    }

    @Override
    public Long visitDisplay(SchemeParser.DisplayContext ctx) {
        Long value = visit(ctx.expression());
        out.print(value);
        return null;
    }

    @Override
    public Long visitMult(SchemeParser.MultContext ctx) {
        BinaryOperator<Long> mult = (x, y) -> x * y;
        return processOperator(ctx.expression(), mult);
    }

    @Override
    public Long visitDiv(SchemeParser.DivContext ctx) {
        BinaryOperator<Long> div = (x, y) -> x / y;
        return processOperator(ctx.expression(), div);
    }

    @Override
    public Long visitPlus(SchemeParser.PlusContext ctx) {
        BinaryOperator<Long> add = (x, y) -> x + y;
        return processOperator(ctx.expression(), add);
    }

    @Override
    public Long visitMinus(SchemeParser.MinusContext ctx) {
        BinaryOperator<Long> minus = (x, y) -> x - y;
        return processOperator(ctx.expression(), minus);
    }

    private Long processOperator(List<SchemeParser.ExpressionContext> expr, BiFunction<Long, Long, Long> biFunc) {
        Long result = 0L;
        if (expr.size() > 0) {
            result = visit(expr.get(0));
            for (int i = 1; i < expr.size(); i++) {
                result = biFunc.apply(result, visit(expr.get(i)));
            }
        }
        return result;
    }

    @Override
    public Long visitNumber(SchemeParser.NumberContext ctx) {
        return Long.parseLong(ctx.number.getText());
    }

    private String getFunctionName(String functionName, int parameterSize) {
        return functionName + "(" + parameterSize + ")";
    }
}
