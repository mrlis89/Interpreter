package com.arnava.interpreter.parsers.lex;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;

class LexemeSplitterTest {

    @Test
    void splitForExpression() {
        LexemeSplitter ls = new LexemeSplitter();
        Collection<String> testArray = ls.split(" INT newVar12 ( 4*9)- 1/2 ");
        assertThat(testArray)
                .isEqualTo(Arrays.asList("INT","newVar12","(","4","*","9",")","-","1","/","2"));
    }
}
