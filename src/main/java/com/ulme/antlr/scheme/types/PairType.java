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
        if (isPairType(car) && isPairType(cdr)) {
            return "(" + car.toString(true) + " " + cdr.toString(false) + ")";
        } else if (isPairType(car)) {
            System.out.println("test");
        } else if (isPairType(cdr)) {
            return "(" + car.toString(true) + " " + cdr.toString(false) + ")";
        }
        return "(" + car.toString(true) + " . " + cdr.toString(false) + ")";
    }

    @Override
    public String toString(boolean hasParen) {
        if (isPairType(car) || isPairType(cdr)) {
            if (hasParen) {
                return "(" + car.toString(true) + " " + cdr.toString(false) + ")";
            } else {
                return car.toString(true) + " " + cdr.toString(false);
            }
        } else {
            if (hasParen) {
                return "(" + car.toString(true) + " . " + cdr.toString(false) + ")";
            } else {
                return car.toString(true) + " . " + cdr.toString(false);
            }
        }
    }

    public Type getCar() {
        return car;
    }

    public Type getCdr() {
        return cdr;
    }
}
