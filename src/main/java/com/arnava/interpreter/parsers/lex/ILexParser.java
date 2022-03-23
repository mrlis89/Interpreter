package com.arnava.interpreter.parsers.lex;

import com.arnava.interpreter.exceptions.BracketsCountException;
import com.arnava.interpreter.exceptions.OperatorOrderException;

import java.util.Collection;

public interface ILexParser {
    Collection<Lexeme> toLexemeArray() throws BracketsCountException, OperatorOrderException;
}
