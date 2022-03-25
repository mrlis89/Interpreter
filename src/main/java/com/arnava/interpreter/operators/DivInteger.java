package com.arnava.interpreter.operators;

import com.arnava.interpreter.scalars.ScalarInteger;
import com.arnava.interpreter.types.IInteger;
import com.arnava.interpreter.types.IScalarType;
/**
 * {@code DivInteger} is class for implementation division operation
 */
public class DivInteger extends BinaryOperator implements IInteger {
    /**
     * Primary constructor that takes two {@code IScalarType} arguments and Initializes {@code DivInteger} object
     */
    public DivInteger(IScalarType arg1, IScalarType arg2) {
        super(arg1, arg2);
    }
    /**
     * Secondary constructor that takes two {@code int} arguments, wraps them and passes into primary constructor.
     * <p></p>
     * Used only for class testing
     */
    public DivInteger(int arg1, int arg2) {
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

    /**
     * @return integer result of division
     */
    @Override
    public Integer toScalar() {
        return getArg1().toScalar() / getArg2().toScalar();
    }
}
