package com.arnava.interpreter.scalars;

import com.arnava.interpreter.types.IInteger;

public final class ScalarInteger implements IInteger {
    private final int value;

    public ScalarInteger(int value) {
        this.value = value;
    }

    @Override
    public Integer toScalar() {
        return value;
    }
}
