package com.ulme.antlr.scheme.types;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ListType extends Type {
    private List<Type> elements = new ArrayList<>();

    public ListType() {
    }

    public void add(Type value) {
        elements.add(value);
    }

    @Override
    public String toString() {
        String values = elements.stream()
                                .map(Object::toString)
                                .collect(Collectors.joining(" "));
        return "(" + values + ")";
    }

    @Override
    public String toString(boolean hasParen) {
        return toString();
    }
}
