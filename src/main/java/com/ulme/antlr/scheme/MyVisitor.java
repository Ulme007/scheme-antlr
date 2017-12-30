package com.ulme.antlr.scheme;

import com.ulme.antlr.scheme.types.LongType;
import com.ulme.antlr.scheme.types.Type;
import org.antlr.v4.runtime.Token;

import java.io.PrintStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;

public class MyVisitor extends SchemeBaseVisitor<Type> {

    private PrintStream out;
    private Map<String, Type> env = new HashMap<>();
    private Map<String, SchemeParser.FunctionDefinitionContext> functions = new HashMap<>();

    MyVisitor(PrintStream out) {
        this.out = out;
    }

    @Override
    public Type visitList(SchemeParser.ListContext ctx) {
        List<SchemeParser.ExpressionContext> expression = ctx.expression();

        return super.visitList(ctx);
    }

    @Override
    public Type visitFunctionCall(SchemeParser.FunctionCallContext ctx) {
        String functionName = getFunctionName(ctx.funcName.getText(), ctx.arguments.size());
        SchemeParser.FunctionDefinitionContext functionDefinitionContext = functions.get(functionName);
        if (functionDefinitionContext == null) {
            throw new UndefinedFunctionException(ctx.funcName, functionName);
        }

        //save global variables map
        Map<String, Type> oldEnv = env;

        // create a local variables map
        env = new HashMap<>();

        // set variables from function call

        List<SchemeParser.ExpressionContext> expressions = ctx.arguments;
        List<Token> declarations = functionDefinitionContext.paramNames;
        for (int i = 0; i < declarations.size(); i++) {
            String variableName = declarations.get(i)
                                              .getText();
            Type value = visit(expressions.get(i));
            env.put(variableName, value);
        }

        Type result = visit(functionDefinitionContext.statements);

        // set back global variables map
        env = oldEnv;

        return result;
    }

    @Override
    public Type visitFunctionDefinition(SchemeParser.FunctionDefinitionContext ctx) {
        String functionName = getFunctionName(ctx.funcName.getText(), ctx.paramNames.size());
        if (functions.containsKey(functionName)) {
            throw new FunctionAlreadyDefinedException(ctx.funcName, functionName);
        }
        functions.put(functionName, ctx);

        return null;
    }

    @Override
    public Type visitIdentifier(SchemeParser.IdentifierContext ctx) {
        String varName = ctx.varName.getText();
        if (!env.containsKey(varName)) {
            throw new UndeclaredVariableException(ctx.varName);
        }
        return env.get(varName);
    }

    @Override
    public Type visitVariableDefinition(SchemeParser.VariableDefinitionContext ctx) {
        String varName = ctx.varName.getText();
        if (env.containsKey(varName)) {
            throw new VariableAlreadyDefinedException(ctx.varName);
        }
        Type varValue = visit(ctx.expression());
        env.put(varName, varValue);
        return null;
    }

    @Override
    public Type visitDisplay(SchemeParser.DisplayContext ctx) {
        Type value = visit(ctx.expression());
        out.print(value);
        return null;
    }

    @Override
    public Type visitArithmeticOperation(SchemeParser.ArithmeticOperationContext ctx) {
        String operator = ctx.oprator.getText();
        BinaryOperator<Long> operation = null;
        switch (operator) {
            case "+":
                operation = (x, y) -> x + y;
                break;
            case "-":
                operation = (x, y) -> x - y;
                break;
            case "*":
                operation = (x, y) -> x * y;
                break;
            case "/":
                operation = (x, y) -> x / y;
                break;
        }
        return processOperator(ctx.expression(), operation);
    }

    private Type processOperator(List<SchemeParser.ExpressionContext> expr, BiFunction<Long, Long, Long> biFunc) {
        Long result = 0L;
        if (expr.size() > 0) {
            LongType longType = (LongType) visit(expr.get(0));
            result = longType.getValue();
            for (int i = 1; i < expr.size(); i++) {
                longType = (LongType) visit(expr.get(i));
                result = biFunc.apply(result, longType.getValue());
            }
        }
        return new LongType(result);
    }

    @Override
    public Type visitNumber(SchemeParser.NumberContext ctx) {
        return new LongType(Long.parseLong(ctx.number.getText()));
    }

    private String getFunctionName(String functionName, int parameterSize) {
        return functionName + "(" + parameterSize + ")";
    }
}
