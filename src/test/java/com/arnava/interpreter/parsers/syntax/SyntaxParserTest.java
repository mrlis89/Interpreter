package com.arnava.interpreter.parsers.syntax;

import com.arnava.interpreter.parsers.lex.LexParser;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class SyntaxParserTest {

    @Test
    void parseForExpression(){
        LexParser lp = new LexParser("3 +5-3 + 1");
        assertThat(
                new SyntaxParser()
                        .toNodeTree(lp.parse())
                        .fromNode()
                        .toScalar())
                .isEqualTo(4);
    }

    @Test
    void parseForOneNumber(){
        LexParser lp = new LexParser("7");
        assertThat(
                new SyntaxParser()
                        .toNodeTree(lp.parse())
                        .fromNode()
                        .toScalar())
                .isEqualTo(7);
    }

    @Test
    void parseForExpressionWithParentheses() {
        LexParser lp = new LexParser("((3 + 1) * 2)*3");
        assertThat(
                new SyntaxParser()
                        .toNodeTree(lp.parse())
                        .fromNode()
                        .toScalar()
        )
                .isEqualTo(24);
    }

}
