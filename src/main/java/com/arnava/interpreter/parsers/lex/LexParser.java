package com.arnava.interpreter.parsers.lex;

import com.arnava.interpreter.exceptions.BracketsCountException;
import com.arnava.interpreter.exceptions.OperatorOrderException;

import java.util.ArrayList;
import java.util.List;

public class LexParser implements ILexParser {
    private final String inputLine;
    private final ILexemeSplitter splitter = new LexemeSplitter();
    private final ILexConverter converter = new LexemeConverter();

    public LexParser(String inputLine) {
        this.inputLine = inputLine;
    }

    @Override
    public List<Lexeme> toLexemeArray() throws BracketsCountException, OperatorOrderException {
        List<Lexeme> lexemes = new ArrayList<>();
        List<String> splitLines = splitter.split(inputLine);
        for (String string : splitLines) {
            lexemes.add(converter.toLexemeFrom(string));
        }
        checkForCorrectInput(lexemes);
        return lexemes;
    }

    public void checkForCorrectInput(List<Lexeme> lexemes) throws OperatorOrderException, BracketsCountException {
        checkForCorrectOperatorsInput(lexemes);
        checkForCorrectBracketsCount(lexemes);
    }

    public void checkForCorrectOperatorsInput(List<Lexeme> lexemes) throws OperatorOrderException {
        for (int i = 0; i < lexemes.size() - 1; i++) {
            if (lexemes.get(i).isOperator() && lexemes.get(i+1).isOperator()) {
                throw new OperatorOrderException("Wrong operator count or number between operators is missing");
            }
        }
    }

    public void checkForCorrectBracketsCount(List<Lexeme> lexemes) throws BracketsCountException {
        int leftBracketCount = 0;
        int rightBracketCount = 0;
        for (Lexeme lexeme : lexemes) {
            if (lexeme.isLeftBracket()) leftBracketCount++;
            if (lexeme.isRightBracket()) rightBracketCount++;
        }
        if (leftBracketCount != rightBracketCount) throw new BracketsCountException("Wrong count of brackets");
    }
}
