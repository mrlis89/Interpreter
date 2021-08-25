package com.arnava.interpreter.parsers.lex;

import java.util.ArrayList;
import java.util.Collection;

public class LexParser implements ILexParser {
    private final String line;
    private ILexemeSplitter splitter = new LexemeSplitter();
    private ILexConverter converter = new LexemeConverter();

    public LexParser(String line) {
        this.line = line;
    }

    @Override
    public Collection<Lexeme> parse() {
        ArrayList<Lexeme> lexemes = new ArrayList<>();
        Collection<String> splitLines = splitter.split(line);
        for (String s: splitLines) {
            lexemes.add(converter.fromString(s));
        }

        return lexemes;
    }
}
