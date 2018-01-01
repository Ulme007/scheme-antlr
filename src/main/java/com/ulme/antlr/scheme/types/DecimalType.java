package com.ulme.antlr.scheme.types;

import java.math.BigDecimal;

public class DecimalType extends Type {

    private BigDecimal value;

    public DecimalType(BigDecimal value) {
        this.value = value;
    }

    public DecimalType(int value) {
        this.value = new BigDecimal(value);
    }

    public DecimalType(String value) {
        this.value = new BigDecimal(value);
    }

    public BigDecimal getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
