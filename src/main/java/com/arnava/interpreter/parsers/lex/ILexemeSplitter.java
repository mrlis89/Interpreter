package com.arnava.interpreter.parsers.lex;

import java.util.Collection;

public interface ILexemeSplitter {
    Collection<String> split(String st);
}
