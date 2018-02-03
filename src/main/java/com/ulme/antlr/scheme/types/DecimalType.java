package com.ulme.antlr.scheme.types;

import java.math.BigDecimal;

import static java.math.BigDecimal.ROUND_UNNECESSARY;

public class DecimalType extends Type {

    private final BigDecimal value;

    public DecimalType(BigDecimal value) {
        this.value = value;
    }

    public DecimalType(int value) {
        this.value = new BigDecimal(value);
    }

    public DecimalType(String value) {
        this.value = new BigDecimal(value);
    }

    public DecimalType add(DecimalType other) {
        return new DecimalType(value.add(other.value));
    }

    public DecimalType subtract(DecimalType other) {
        return new DecimalType(value.subtract(other.value));
    }

    public DecimalType multiply(DecimalType other) {
        return new DecimalType(value.multiply(other.value));
    }

    public DecimalType divide(DecimalType other) {
        return new DecimalType(value.divide(other.value, ROUND_UNNECESSARY));
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @Override
    public String toString(boolean hasParen) {
        return toString();
    }
}
