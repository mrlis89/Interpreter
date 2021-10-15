package com.arnava.interpreter.parsers.syntax;

import com.arnava.interpreter.parsers.lex.LexParser;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

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
}
