package com.arnava.interpreter.operators;

import com.arnava.interpreter.scalars.ScalarInteger;
import com.arnava.interpreter.types.IInteger;
import com.arnava.interpreter.types.IScalarType;

/**
 * {@code ModInteger} is class for implementation modulus operation
 */
public class ModInteger extends UnaryOperator implements IInteger {
    /**
     * Primary constructor that takes one {@code IInteger} argument and Initializes {@code ModInteger} object
     */
    protected ModInteger(IScalarType arg1) {
        super(arg1);
    }
    /**
     * Secondary constructor that takes one {@code int} arguments, wraps it and passes into primary constructor.
     * <p></p>
     * Used only for class testing
     */
    public ModInteger(int arg1) {
        this(new ScalarInteger(arg1));
    }
    public IInteger getArg() {
        return (IInteger) super.getArg();
    }

    /**
     * @return absolute value of number (modulus)
     */
    @Override
    public Integer toScalar() {
        int value = getArg().toScalar();
        if (value < 0) {
            return value * (-1);
        } else return value;
    }

}
