package com.arnava.interpreter.operators;

import com.arnava.interpreter.scalars.ScalarInteger;
import com.arnava.interpreter.types.IInteger;
import com.arnava.interpreter.types.IScalarType;
/**
 * {@code MinusInteger} is class for implementation subtraction operation
 */
public class MinusInteger extends BinaryOperator implements IInteger {
    /**
     * Primary constructor that takes two {@code IScalarType} arguments and Initializes {@code MinusInteger} object
     */
    public MinusInteger(IScalarType arg1, IScalarType arg2) {
        super(arg1, arg2);
    }
    /**
     * Secondary constructor that takes two {@code int} arguments, wraps them and passes into primary constructor.
     * <p></p>
     * Used only for class testing
     */
    public MinusInteger(int arg1, int arg2) {
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
     * @return integer result of subtraction
     */
    @Override
    public Integer toScalar() {
        return getArg1().toScalar() - getArg2().toScalar();
    }
}
