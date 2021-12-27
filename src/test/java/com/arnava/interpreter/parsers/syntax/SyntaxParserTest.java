package com.arnava.interpreter.parsers.syntax;

import com.arnava.interpreter.parsers.lex.LexParser;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class SyntaxParserTest {

    @Test
    void parseForIntVar() {
        LexParser lp = new LexParser("INT var = 3");
        SyntaxParser sp = new SyntaxParser();
        sp.parse(lp.parse());
        assertThat(sp.printValueOf("var")).isEqualTo("3");
    }

    @Test
    void parseForStrVar() {
        LexParser lp = new LexParser("STR var = \"hello\"");
        SyntaxParser sp = new SyntaxParser();
        sp.parse(lp.parse());
        assertThat(sp.printValueOf("var")).isEqualTo("hello");
    }

    @Test
    void parseForExpression(){
        LexParser lp = new LexParser("3 +5-3 + 1");
        assertThat(
                new SyntaxParser()
                        .parseExpression(lp.parse())
                        .fromNode()
                        .toScalar())
                .isEqualTo(4);
    }

    @Test
    void parseForExpressionWithDifferentOpers(){
        LexParser lp = new LexParser("(3 * 5) - 3 * 1");
        assertThat(
                new SyntaxParser()
                        .parseExpression(lp.parse())
                        .fromNode()
                        .toScalar())
                .isEqualTo(12);
    }

    @Test
    void parseForOneNumber(){
        LexParser lp = new LexParser("7");
        assertThat(
                new SyntaxParser()
                        .parseExpression(lp.parse())
                        .fromNode()
                        .toScalar())
                .isEqualTo(7);
    }

    @Test
    void parseForExpressionWithParentheses() {
        LexParser lp = new LexParser("((3 + 1) * 2)*3");
        assertThat(
                new SyntaxParser()
                        .parseExpression(lp.parse())
                        .fromNode()
                        .toScalar()
        )
                .isEqualTo(24);
    }

}
