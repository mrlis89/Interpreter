package com.arnava.interpreter.parsers.syntax;

import com.arnava.interpreter.parsers.lex.LexTypes;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SyntaxNode that = (SyntaxNode) o;
        return Objects.equals(value, that.value) && Objects.equals(args, that.args);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, args);
    }

    @Override
    public IScalarType fromNode() {
        return value.getType().createNew(value, args);
    }
}
