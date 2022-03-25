package com.arnava.interpreter.operators;

import com.arnava.interpreter.types.IScalarType;
/**
 * {@code BinaryOperator} is basic class for implementation unary math operation
 * such as modulus which has one input parameter {@code arg1}
 */
public abstract class UnaryOperator implements IOperator{
    private final IScalarType arg1;

    protected UnaryOperator(IScalarType arg1) {
        this.arg1 = arg1;
    }

    public IScalarType getArg(){
        return arg1;
    }
}
