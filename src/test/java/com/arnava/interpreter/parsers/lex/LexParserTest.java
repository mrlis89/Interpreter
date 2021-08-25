package com.arnava.interpreter.parsers.lex;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;

class LexParserTest {

    @Test
    void parseForOneNumber() {
        assertThat(
            actual("2")
        ).usingRecursiveComparison().isEqualTo(
            expected(
                new Lexeme(
                    LexTypes.NUMBER,
                    "2"
                )
            )
        );
    }

    @Test
    void parse() {
        assertThat(
            actual("2 + 3")
        ).usingRecursiveComparison().isEqualTo(
            expected(
                new Lexeme(
                    LexTypes.NUMBER,
                    "2"
                ),
                new Lexeme(LexTypes.PLUS),
                new Lexeme(
                    LexTypes.NUMBER,
                    "3"
                )
            )
        );
    }

    private Collection<Lexeme> actual(String value) {
        return new LexParser(value).parse();
    }

    private Collection<Lexeme> expected(Lexeme... values) {
        ArrayList<Lexeme> res = new ArrayList<>();
        for(Lexeme v: values) {
            res.add(v);
        }
        return res;
    }
}
