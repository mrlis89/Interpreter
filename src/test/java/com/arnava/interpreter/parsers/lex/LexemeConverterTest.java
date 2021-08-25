package com.arnava.interpreter.parsers.lex;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class LexemeConverterTest {

    @Test
    void fromStringForPlusOperator() {
        assertThat(
                new LexemeConverter()
                        .fromString("+")
        ).isEqualTo(
                new Lexeme(LexTypes.PLUS)
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