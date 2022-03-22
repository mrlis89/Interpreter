package com.arnava.interpreter.parsers.lex;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class LexParser implements ILexParser {
    private final String line;
    private final ILexemeSplitter splitter = new LexemeSplitter();
    private final ILexConverter converter = new LexemeConverter();

    public LexParser(String line) {
        this.line = line;
    }

    @Override
    public List<Lexeme> parse() {
        List<Lexeme> lexemes = new ArrayList<>();
        List<String> splitLines = splitter.split(line);
        for (String st : splitLines) {
            lexemes.add(converter.fromString(st));
        }
        return lexemes;
    }
}
