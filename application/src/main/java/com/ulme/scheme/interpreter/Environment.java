package com.ulme.scheme.interpreter;

import com.ulme.scheme.interpreter.types.Type;

import java.util.HashMap;
import java.util.Map;

class Environment {

    private Map<String, Type> env = new HashMap<>();
    private Map<String, SchemeParser.FunctionDefinitionContext> functions = new HashMap<>();

    void putVariable(String variableName, Type value) {
        env.put(variableName, value);
    }

    Type getVariable(String variableName) {
        return env.get(variableName);
    }

    boolean containsVariable(String variableName) {
        return env.containsKey(variableName);
    }

    void putFunction(String variableName, SchemeParser.FunctionDefinitionContext value) {
        functions.put(variableName, value);
    }

    SchemeParser.FunctionDefinitionContext getFunction(String variableName) {
        return functions.get(variableName);
    }

    boolean containsFunction(String variableName) {
        return functions.containsKey(variableName);
    }
}
