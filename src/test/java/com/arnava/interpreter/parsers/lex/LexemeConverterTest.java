package com.arnava.interpreter.parsers.lex;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class LexemeConverterTest {

    @Test
    void fromStringForOperator() {
        assertThat(
                new LexemeConverter()
                        .fromString("-")
        ).isEqualTo(
                new Lexeme(LexTypes.MINUS)
        );
    }

    @Test
    void fromStringForNumber() {
        assertThat(
                new LexemeConverter()
                        .fromString("193")
        ).isEqualTo(
                new Lexeme(LexTypes.NUMBER,"193")
        );
    }
}