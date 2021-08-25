package com.arnava.interpreter.operators;

import com.arnava.interpreter.scalars.ScalarInteger;
import com.arnava.interpreter.types.IInteger;

public class ModInteger extends UnaryOperator implements IInteger {
    protected ModInteger(IInteger arg1) {
        super(arg1);
    }

    public ModInteger(int arg1) {
        this(new ScalarInteger(arg1));
    }

    @Override
    public Integer toScalar() {
        int value = getArg().toScalar();
        if (value < 0) {
            return value * (-1);
        } else return value;
    }

    public IInteger getArg() {
        return (IInteger) super.getArg();
    }

}
