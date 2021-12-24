package com.arnava.interpreter.vars;

import com.arnava.interpreter.parsers.syntax.SyntaxNode;

public class StrVar implements IVars {
    private final String name;
    private final SyntaxNode value;

    public StrVar(String name, SyntaxNode value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toScalar() {
        return (String) value.fromNode()
                .toScalar();
    }
}
