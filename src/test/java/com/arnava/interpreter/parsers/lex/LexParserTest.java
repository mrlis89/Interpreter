package com.arnava.interpreter.parsers.lex;

import com.arnava.interpreter.exceptions.BracketsCountException;
import com.arnava.interpreter.exceptions.OperatorOrderException;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class LexParserTest {

    @Test
    void parseForOneNumber() throws BracketsCountException, OperatorOrderException {
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
    void parseForExpressionWithSpaces() throws BracketsCountException, OperatorOrderException {
        assertThat(
                getActual(" 21 - 3+5")
        ).usingRecursiveComparison().isEqualTo(
                getExpected(
                        new Lexeme(
                                LexTypes.NUMBER,
                                "21"
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
                        )
                )
        );
    }

    private Collection<Lexeme> getActual(String value) throws BracketsCountException, OperatorOrderException {
        return new LexParser(value).toLexemeArray();
    }

    private Collection<Lexeme> getExpected(Lexeme... values) {
        return Arrays.asList(values);
    }

    @Test
    void containsBrackets() throws BracketsCountException, OperatorOrderException {
        LexParser lp = new LexParser("7 * (3 + 1) + 2");
        assertThat(
                lp
                        .toLexemeArray()
                        .contains(new Lexeme(LexTypes.LEFT_BRACKET))
        ).isTrue();
    }

    @Test
    void checkForCorrectOperatorsInput() {
        assertThatThrownBy(() -> {
                    LexParser lp = new LexParser("4 /* 7");
                    lp.toLexemeArray();
                }
        ).isInstanceOf(OperatorOrderException.class);
    }

    @Test
    void checkForCorrectBracketsCount() {
        assertThatThrownBy(() -> {
                    LexParser lp = new LexParser("((4 * 7)");
                    lp.toLexemeArray();
                }
        ).isInstanceOf(BracketsCountException.class);
    }
}
