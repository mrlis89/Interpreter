package com.arnava.interpreter.operators;

import com.arnava.interpreter.types.IScalarType;

/**
 * {@code BinaryOperator} is basic class for implementation binary math operation
 * such as minus and plus which have two input parameters {@code arg1} and {@code arg2}
 */
public abstract class BinaryOperator implements IOperator{
    private final IScalarType arg1, arg2;

    /**
     *
     * @param arg1 first argument
     * @param arg2 second argument
     *             <blockquote>arg1 operator arg2</blockquote>
     */
    protected BinaryOperator(IScalarType arg1, IScalarType arg2) {
        this.arg1 = arg1;
        this.arg2 = arg2;
    }

    public IScalarType getArg1() {
        return arg1;
    }

    public IScalarType getArg2() {
        return arg2;
    }
}
