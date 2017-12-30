package com.ulme.antlr.scheme.types;

public class LongType extends Type {

    private Long value;

    public LongType(Long value) {
        this.value = value;
    }

    public Long getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
