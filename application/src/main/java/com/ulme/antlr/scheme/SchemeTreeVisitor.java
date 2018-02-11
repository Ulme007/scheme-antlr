package com.ulme.antlr.scheme;

import com.ulme.antlr.scheme.types.DecimalType;
import com.ulme.antlr.scheme.types.ListType;
import com.ulme.antlr.scheme.types.Type;
import org.antlr.v4.runtime.Token;

import java.io.PrintStream;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;

public class SchemeTreeVisitor extends SchemeBaseVisitor<Type> {

    private Environment env = new Environment();
    private PrintStream out;

    SchemeTreeVisitor(PrintStream out) {
        this.out = out;
    }

    @Override
    public Type visitList(SchemeParser.ListContext ctx) {
        List<SchemeParser.ExpressionContext> expressions = ctx.expression();

        ListType result = new ListType();
        for (SchemeParser.ExpressionContext expression :
                expressions) {
            Type value = visit(expression);
            result.add(value);
        }
        return result;
    }

    @Override
    public Type visitFunctionCall(SchemeParser.FunctionCallContext ctx) {
        String functionName = getFunctionName(ctx.funcName.getText(), ctx.arguments.size());
        SchemeParser.FunctionDefinitionContext functionDefinitionContext = env.getFunction(functionName);
        if (functionDefinitionContext == null) {
            throw new UndefinedFunctionException(ctx.funcName, functionName);
        }

        //save global variables map
        Environment oldEnv = env;

        // create a local variables map
        env = new Environment();

        // set variables from function call
        List<SchemeParser.ExpressionContext> expressions = ctx.arguments;
        List<Token> declarations = functionDefinitionContext.paramNames;
        for (int i = 0; i < declarations.size(); i++) {
            String variableName = declarations.get(i)
                                              .getText();
            Type value = visit(expressions.get(i));
            env.putVariable(variableName, value);
        }

        Type result = visit(functionDefinitionContext.statements);

        // set back global variables map
        env = oldEnv;

        return result;
    }

    @Override
    public Type visitFunctionDefinition(SchemeParser.FunctionDefinitionContext ctx) {
        String functionName = getFunctionName(ctx.funcName.getText(), ctx.paramNames.size());
        if (env.containsFunction(functionName)) {
            throw new FunctionAlreadyDefinedException(ctx.funcName, functionName);
        }
        env.putFunction(functionName, ctx);

        return null;
    }

    @Override
    public Type visitIdentifier(SchemeParser.IdentifierContext ctx) {
        String varName = ctx.varName.getText();
        if (!env.containsVariable(varName)) {
            throw new UndeclaredVariableException(ctx.varName);
        }
        return env.getVariable(varName);
    }

    @Override
    public Type visitVariableDefinition(SchemeParser.VariableDefinitionContext ctx) {
        String varName = ctx.varName.getText();
        if (env.containsVariable(varName)) {
            throw new VariableAlreadyDefinedException(ctx.varName);
        }
        Type varValue = visit(ctx.expression());
        env.putVariable(varName, varValue);
        return null;
    }

    @Override
    public Type visitDisplay(SchemeParser.DisplayContext ctx) {
        Type value = visit(ctx.expression());
        out.print(value);
        return null;
    }

    @Override
    public Type visitNewline(SchemeParser.NewlineContext ctx) {
        out.println();
        return null;
    }

    @Override
    public Type visitArithmeticOperation(SchemeParser.ArithmeticOperationContext ctx) {
        String operator = ctx.oprator.getText();
        BinaryOperator<DecimalType> operation = null;
        switch (operator) {
            case "+":
                operation = DecimalType::add;
                break;
            case "-":
                operation = DecimalType::subtract;
                break;
            case "*":
                operation = DecimalType::multiply;
                break;
            case "/":
                operation = DecimalType::divide;
                break;
        }
        return processOperator(ctx.expression(), operation);
    }

    private Type processOperator(List<SchemeParser.ExpressionContext> expr, BiFunction<DecimalType, DecimalType, DecimalType> biFunc) {
        DecimalType result = null;
        if (expr.size() > 0) {
            DecimalType decimalType = (DecimalType) visit(expr.get(0));
            result = decimalType;
            for (int i = 1; i < expr.size(); i++) {
                decimalType = (DecimalType) visit(expr.get(i));
                result = biFunc.apply(result, decimalType);
            }
        }
        return result;
    }

    @Override
    public Type visitNumber(SchemeParser.NumberContext ctx) {
        return new DecimalType(ctx.number.getText());
    }

    private String getFunctionName(String functionName, int parameterSize) {
        return functionName + "(" + parameterSize + ")";
    }
}
