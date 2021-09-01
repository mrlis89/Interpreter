package com.arnava.interpreter.parsers.syntax;

import com.arnava.interpreter.parsers.lex.Lexeme;

import java.util.Collection;
import java.util.Objects;

public class SyntaxNode {
    private Lexeme value;
    private Collection<SyntaxNode> args;

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
}
