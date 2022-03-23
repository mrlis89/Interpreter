package com.arnava.interpreter.parsers.lex;

import com.arnava.interpreter.exceptions.BracketsCountException;
import com.arnava.interpreter.exceptions.OperatorOrderException;

import com.arnava.interpreter.exceptions.SyntaxErrorException;
import java.util.Collection;

public interface ILexParser {
    Collection<Lexeme> toLexemeArray() throws BracketsCountException, OperatorOrderException, SyntaxErrorException;
}
