package com.arnava.interpreter.calculator;

import com.arnava.interpreter.exceptions.BracketsCountException;
import com.arnava.interpreter.exceptions.OperatorOrderException;
import com.arnava.interpreter.exceptions.SyntaxErrorException;

public interface ICalculator {
    Integer calculate(String st) throws SyntaxErrorException;
}
