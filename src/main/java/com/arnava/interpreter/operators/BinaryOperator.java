package com.arnava.interpreter.operators;

import com.arnava.interpreter.types.IScalarType;

public abstract class BinaryOperator implements IOperator{
    private final IScalarType arg1, arg2;

    public IScalarType getArg1() {
        return arg1;
    }

    public IScalarType getArg2() {
        return arg2;
    }

    protected BinaryOperator(IScalarType arg1, IScalarType arg2) {
        this.arg1 = arg1;
        this.arg2 = arg2;
    }
}
