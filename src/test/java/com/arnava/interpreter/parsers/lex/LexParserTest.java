package com.arnava.interpreter.parsers.lex;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;

class LexParserTest {

    @Test
    void parseForOneNumber() {
        assertThat(
                getActual("124")
        ).usingRecursiveComparison().isEqualTo(
                getExpected(
                        new Lexeme(
                                LexTypes.NUMBER,
                                "124"
                        )
                )
        );
    }

    @Test
    void parseForShortExpressionWithSpaces() {
        assertThat(
                getActual(" 2 - 3 ")
        ).usingRecursiveComparison().isEqualTo(
                getExpected(
                        new Lexeme(
                                LexTypes.NUMBER,
                                "2"
                        ),
                        new Lexeme(LexTypes.MINUS),
                        new Lexeme(
                                LexTypes.NUMBER,
                                "3"
                        )
                )
        );
    }

    @Test
    void parseForLongExpressionWithSpaces() {
        assertThat(
                getActual(" 2 - 3+5 - 7")
        ).usingRecursiveComparison().isEqualTo(
                getExpected(
                        new Lexeme(
                                LexTypes.NUMBER,
                                "2"
                        ),
                        new Lexeme(LexTypes.MINUS),
                        new Lexeme(
                                LexTypes.NUMBER,
                                "3"
                        ),
                        new Lexeme(LexTypes.PLUS),
                        new Lexeme(
                                LexTypes.NUMBER,
                                "5"
                        ),
                        new Lexeme(
                                LexTypes.MINUS
                        ),
                        new Lexeme(
                                LexTypes.NUMBER,
                                "7"
                        )
                )
        );
    }

    @Test
    void containsParentheses() {
        LexParser lp = new LexParser("7 * (3 + 1) + 2");
        assertThat(
                lp
                        .parse()
                        .contains(new Lexeme(LexTypes.LEFT_PARENTHESIS))
        ).isTrue();
    }

    private Collection<Lexeme> getActual(String value) {
        return new LexParser(value).parse();
    }

    private Collection<Lexeme> getExpected(Lexeme... values) {
        return Arrays.asList(values);
    }
}
