package com.ulme.antlr.scheme.types;

public class PairType extends Type {

    private final Type car;
    private final Type cdr;

    public PairType(Type car, Type cdr) {
        this.car = car;
        this.cdr = cdr;
    }

    private boolean isPairType(Type type) {
        return type.getClass()
                   .equals(PairType.class);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("(");
        result.append(car.toString(true));
        if (isPairType(cdr)) {
            result.append(" ");
        } else {
            result.append(" . ");
        }
        result.append(cdr.toString(false));
        result.append(")");
        return result.toString();
    }

    @Override
    public String toString(boolean hasParen) {
        StringBuilder result = new StringBuilder();
        if (hasParen) {
            result.append("(");
        }
        result.append(car.toString(true));
        if (isPairType(car) || isPairType(cdr)) {
            result.append(" ");
        } else {
            result.append(" . ");
        }
        result.append(cdr.toString(false));
        if (hasParen) {
            result.append(")");
        }
        return result.toString();
    }
}
