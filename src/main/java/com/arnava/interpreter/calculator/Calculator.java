package com.arnava.interpreter.calculator;

import com.arnava.interpreter.exceptions.BracketsCountException;
import com.arnava.interpreter.exceptions.OperatorOrderException;
import com.arnava.interpreter.parsers.lex.LexParser;
import com.arnava.interpreter.parsers.syntax.SyntaxParser;

public class Calculator implements ICalculator {
    private final SyntaxParser syntaxParser = new SyntaxParser();

    @Override
    public Integer calculate(String expression) throws BracketsCountException, OperatorOrderException {
        LexParser lp = new LexParser(expression);
        return (Integer) syntaxParser
                .toNodeFrom(lp.toLexemeArray())
                .fromNode()
                .toScalar();
    }

}
