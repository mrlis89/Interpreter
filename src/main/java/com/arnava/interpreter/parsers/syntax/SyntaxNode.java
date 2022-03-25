package com.arnava.interpreter.parsers.syntax;

import com.arnava.interpreter.parsers.lex.Lexeme;
import com.arnava.interpreter.types.IScalarType;

import java.util.*;

/**
 * The {@code Syntax Node} is basic unit in the Node tree, every node contains a parent(Lexeme)
 * and two child(also Syntax Node). So one Node can contain inside Node Tree.
 */
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

    /**
     * The method converts Node object to Scalar Type object using Lexeme type context.
     * @see com.arnava.interpreter.parsers.lex.LexTypes
     */
    @Override
    public IScalarType fromNode() {
        return value.getType().createNew(value, args);
    }

}
