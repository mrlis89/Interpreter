package com.arnava.interpreter.calculator;

import com.arnava.interpreter.exceptions.BracketsCountException;
import com.arnava.interpreter.exceptions.OperatorOrderException;

public interface ICalculator {
    Integer calculate(String st) throws BracketsCountException, OperatorOrderException;
}
