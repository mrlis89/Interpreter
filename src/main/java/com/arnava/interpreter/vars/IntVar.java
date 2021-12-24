package com.arnava.interpreter.vars;

import com.arnava.interpreter.parsers.syntax.SyntaxNode;
import com.arnava.interpreter.types.IInteger;

public class IntVar implements IVars {
    private final String name;
    private final SyntaxNode value;

    public IntVar(String name,SyntaxNode value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    @Override
    public Integer toScalar() {
        return (Integer) value.fromNode()
                .toScalar();
    }
}
