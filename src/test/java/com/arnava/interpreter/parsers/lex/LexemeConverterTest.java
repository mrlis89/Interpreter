package com.arnava.interpreter.parsers.lex;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class LexemeConverterTest {

    @Test
    void fromStringForOperator() {
        assertThat(
                new LexemeConverter()
                        .toLexemeFrom("-")
        ).isEqualTo(
                new Lexeme(LexTypes.MINUS)
        );
    }

    @Test
    void fromStringForNumber() {
        assertThat(
                new LexemeConverter()
                        .toLexemeFrom("193")
        ).isEqualTo(
                new Lexeme(LexTypes.NUMBER,"193")
        );
    }

    @Test
    void fromStringForStringValue() {
        String line = "\"Name12\"";
        assertThat(
                new LexemeConverter()
                        .toLexemeFrom(line)
        ).isEqualTo(
                new Lexeme(LexTypes.STRING,line)
        );
    }
}