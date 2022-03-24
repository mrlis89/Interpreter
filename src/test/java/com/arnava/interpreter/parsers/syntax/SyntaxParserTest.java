package com.arnava.interpreter.parsers.syntax;

import com.arnava.interpreter.exceptions.BracketsCountException;
import com.arnava.interpreter.exceptions.OperatorOrderException;
import com.arnava.interpreter.exceptions.SyntaxErrorException;
import com.arnava.interpreter.parsers.lex.LexParser;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class SyntaxParserTest {

    @Test
    void parseForIntVar() throws SyntaxErrorException {
        LexParser lp = new LexParser("INT var = 3");
        SyntaxParser sp = new SyntaxParser();
        sp.parse(lp.toLexemeArray());
        assertThat(sp.printValueOf("var")).isEqualTo("3");
    }

    @Test
    void parseForStrVar() throws SyntaxErrorException {
        LexParser lp = new LexParser("STR var = \"hello\"");
        SyntaxParser sp = new SyntaxParser();
        sp.parse(lp.toLexemeArray());
        assertThat(sp.printValueOf("var")).isEqualTo("hello");
    }

    @Test
    void parseForExpression() throws SyntaxErrorException {
        LexParser lp = new LexParser("13 +5-3 + 1");
        assertThat(
                new SyntaxParser()
                        .toNodeFrom(lp.toLexemeArray())
                        .fromNode()
                        .toScalar())
                .isEqualTo(16);
    }

    @Test
    void parseForExpressionWithDifferentOpers() throws SyntaxErrorException {
        LexParser lp = new LexParser("(3 * 5)-  3 * 1");
        assertThat(
                new SyntaxParser()
                        .toNodeFrom(lp.toLexemeArray())
                        .fromNode()
                        .toScalar())
                .isEqualTo(12);
    }

    @Test
    void parseForOneNumber() throws SyntaxErrorException {
        LexParser lp = new LexParser("7");
        assertThat(
                new SyntaxParser()
                        .toNodeFrom(lp.toLexemeArray())
                        .fromNode()
                        .toScalar())
                .isEqualTo(7);
    }

    @Test
    void parseForExpressionWithBrackets() throws SyntaxErrorException {
        LexParser lp = new LexParser("((3 + 1) * 2)*11");
        assertThat(
                new SyntaxParser()
                        .toNodeFrom(lp.toLexemeArray())
                        .fromNode()
                        .toScalar()
        )
                .isEqualTo(88);
    }

}
