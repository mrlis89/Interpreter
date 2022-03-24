package com.arnava.interpreter.scalars;

import com.arnava.interpreter.types.IInteger;

/**
 * {@code ScalarInteger} class is a basic wrapper class that contains only  {@code int} value.
 */
public final class ScalarInteger implements IInteger {
    private final int value;

    /**
     * Initializes a newly created {@code ScalarInteger} object
     * @param value
     *              integer number
     */
    public ScalarInteger(int value) {
        this.value = value;
    }

    /**
     *
     * @return integer {@code value} from private class field
     */
    @Override
    public Integer toScalar() {
        return value;
    }
}
