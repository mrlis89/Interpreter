package com.arnava.interpreter.parsers.lex;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

class LexemeTest {

    @Test
    void getType() {
        assertThat(
                new Lexeme(LexTypes.NUMBER,"12")
                .getType()
        ).isEqualTo(LexTypes.NUMBER);
    }

    @Test
    void getValue() {
        assertThat(
                new Lexeme(LexTypes.NUMBER,"12")
                        .getValue()
        ).isEqualTo("12");
    }

    @Test
    void testEquals() {
        assertThat(
                new Lexeme(LexTypes.NUMBER,"12")
                        .equals(
                                new Lexeme(LexTypes.NUMBER,"12"))

        ).isTrue();

        assertThat(
                new Lexeme(LexTypes.NUMBER,"12")
                        .equals(null)

        ).isFalse();


    }
}