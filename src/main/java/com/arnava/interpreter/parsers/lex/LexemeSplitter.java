package com.arnava.interpreter.parsers.lex;

import java.util.Arrays;
import java.util.Collection;

public class LexemeSplitter implements ILexemeSplitter {
    @Override
    public Collection<String> split(String st) {
        return Arrays.asList(
            st.split(" ")
        );
    }
}
