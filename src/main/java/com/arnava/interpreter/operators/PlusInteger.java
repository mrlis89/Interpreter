package com.arnava.interpreter.operators;

import com.arnava.interpreter.types.IInteger;
import com.arnava.interpreter.scalars.ScalarInteger;

public class PlusInteger extends BinaryOperator implements IInteger {

    public PlusInteger(IInteger arg1, IInteger arg2) {
        super(arg1, arg2);
    }

    public PlusInteger(int arg1, int arg2) {
        this(
            new ScalarInteger(arg1),
            new ScalarInteger(arg2)
        );
    }

    @Override
    public IInteger getArg1() {
        return (IInteger)super.getArg1();
    }

    @Override
    public IInteger getArg2() {
        return (IInteger)super.getArg2();
    }

    @Override
    public Integer toScalar() {
        return getArg1().toScalar() + getArg2().toScalar();
    }
}
