package com.arnava.interpreter.operators;

import com.arnava.interpreter.types.IScalarType;

public abstract class UnaryOperator implements IOperator{
    private final IScalarType arg1;

    protected UnaryOperator(IScalarType arg1) {
        this.arg1 = arg1;
    }

    public IScalarType getArg(){
        return arg1;
    }
}
