package com.arnava.interpreter.calculator;

import com.arnava.interpreter.parsers.lex.LexParser;
import com.arnava.interpreter.parsers.syntax.SyntaxParser;

public class Calculator implements ICalculator {
    private SyntaxParser syntaxParser = new SyntaxParser();

    @Override
    public Integer calculate(String st) {
        LexParser lp = new LexParser(st);
        return (Integer) syntaxParser
                .toNodeTree(lp.parse())
                .fromNode()
                .toScalar();
    }

}
