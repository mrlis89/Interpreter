package com.arnava.interpreter.calculator;

import com.arnava.interpreter.exceptions.BracketsCountException;
import com.arnava.interpreter.exceptions.OperatorOrderException;
import com.arnava.interpreter.exceptions.SyntaxErrorException;
import com.arnava.interpreter.parsers.lex.LexParser;
import com.arnava.interpreter.parsers.syntax.SyntaxParser;

/**
 * {@code Calculator} is a class that contains only {@code calculate} method which calculate given expression.
 */
public class Calculator implements ICalculator {
    private final SyntaxParser syntaxParser = new SyntaxParser();

    /**
     *
     * @param expression is a math expression
     * @return integer result
     * @throws SyntaxErrorException if expression has syntax error
     */
    @Override
    public Integer calculate(String expression) throws SyntaxErrorException {
        LexParser lp = new LexParser(expression);
        return (Integer) syntaxParser
                .toNodeFrom(lp.toLexemeArray())
                .fromNode()
                .toScalar();
    }

}
