package com.arnava.interpreter.scalars;

import com.arnava.interpreter.types.IString;

public final class ScalarString implements IString {
    private final String value;

    public ScalarString(String value) {
        this.value = value;
    }

    @Override
    public String toScalar() {
        return value;
    }
}
