package com.arnava.interpreter.parsers.lex;

import com.arnava.interpreter.exceptions.BracketsCountException;
import com.arnava.interpreter.exceptions.OperatorOrderException;

import com.arnava.interpreter.exceptions.SyntaxErrorException;
import java.util.ArrayList;
import java.util.List;

/**
 *{@code LexParser} converts array of string elements into array of {@code Lexeme}.
 * Class has two object inside to split entered line and convert result into Lexeme array
 */
public class LexParser implements ILexParser {
    private final String inputLine;
    private final ILexemeSplitter splitter = new LexemeSplitter();
    private final ILexConverter converter = new LexemeConverter();

    public LexParser(String inputLine) {
        this.inputLine = inputLine;
    }
    /**
     *Converts {@code inputLine} into array of {@code Lexeme}.
     * @see Lexeme
     */
    @Override
    public List<Lexeme> toLexemeArray() throws SyntaxErrorException {
        List<Lexeme> lexemes = new ArrayList<>();
        List<String> splitLines = splitter.split(inputLine);
        for (String string : splitLines) {
            lexemes.add(converter.toLexemeFrom(string));
        }
        checkForCorrectInput(lexemes);
        return lexemes;
    }

    /**
     * Method checks syntax format of entered line
     * @param lexemes array of {@code Lexeme}
     * @throws SyntaxErrorException if we have OperatorOrderException or BracketsCountException
     */
    public void checkForCorrectInput(List<Lexeme> lexemes) throws SyntaxErrorException {
        checkForCorrectOperatorsInput(lexemes);
        checkForCorrectBracketsCount(lexemes);
    }

    /**
     * Method checks syntax format of entered operators
     * @param lexemes array of Lexeme
     * @throws OperatorOrderException if line contains operator entered next to another operator
     */
    public void checkForCorrectOperatorsInput(List<Lexeme> lexemes) throws OperatorOrderException {
        for (int i = 0; i < lexemes.size() - 1; i++) {
            if (lexemes.get(i).isOperator() && lexemes.get(i+1).isOperator()) {
                throw new OperatorOrderException("Wrong operator count or number between operators is missing");
            }
        }
    }

    /**
     * Method checks syntax format of entered brackets
     * @param lexemes array of Lexeme
     * @throws BracketsCountException if line contains incorrect count of brackets
     */
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
