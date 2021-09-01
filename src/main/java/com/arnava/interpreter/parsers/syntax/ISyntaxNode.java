package com.arnava.interpreter.parsers.syntax;

import com.arnava.interpreter.types.IScalarType;

public interface ISyntaxNode {
    IScalarType fromNode();
}
