package com.arnava.interpreter.parsers.lex;

import com.arnava.interpreter.parsers.syntax.SyntaxNode;
import com.arnava.interpreter.types.IScalarType;

import java.util.Collection;

public interface ILexTypes {
    IScalarType createNew(Lexeme value, Collection<SyntaxNode> args);
}
