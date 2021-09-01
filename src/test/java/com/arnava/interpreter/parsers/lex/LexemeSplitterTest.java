package com.arnava.interpreter.parsers.lex;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;

class LexemeSplitterTest {

    @Test
    void split() {
        LexemeSplitter ls = new LexemeSplitter();
        Collection<String> testArray = ls.split("2 + 2");
        assertThat(testArray)
                .isEqualTo(Arrays.asList("2","+","2"));
    }
}
