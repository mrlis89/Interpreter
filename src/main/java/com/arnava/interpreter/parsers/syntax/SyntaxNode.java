package com.arnava.interpreter.parsers.syntax;

import com.arnava.interpreter.parsers.lex.Lexeme;
import com.arnava.interpreter.types.IScalarType;

import java.util.*;

public class SyntaxNode implements ISyntaxNode {
    private Lexeme value;
    private List<SyntaxNode> args;

    public SyntaxNode(Lexeme value, List<SyntaxNode> args) {
        this.value = value;
        this.args = args;
    }

    public SyntaxNode(Lexeme value) {
        this.value = value;
    }

    @Override
    public IScalarType fromNode() {
        return value.getType().createNew(value, args);
    }

}
