package com.arnava.interpreter.parsers.syntax;

import com.arnava.interpreter.parsers.lex.LexTypes;
import com.arnava.interpreter.parsers.lex.Lexeme;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class SyntaxNodeTest {

    @Test
    void fromNode() {
        List<SyntaxNode> args = Arrays
                .asList(
                        new SyntaxNode(
                                new Lexeme(LexTypes.NUMBER, "3")
                        ),
                        new SyntaxNode(
                                new Lexeme(LexTypes.NUMBER, "5")
                        )
                );

        assertThat(
                new SyntaxNode(
                        new Lexeme(LexTypes.PLUS),
                        args
                ).fromNode()
                        .toScalar()
        ).isEqualTo(8);
    }
}