package com.arnava.interpreter.parsers.lex;

import com.arnava.interpreter.operators.MinusInteger;
import com.arnava.interpreter.operators.PlusInteger;
import com.arnava.interpreter.parsers.syntax.SyntaxNode;
import com.arnava.interpreter.scalars.ScalarInteger;
import com.arnava.interpreter.types.IScalarType;

import java.util.List;

public enum LexTypes {
    //Scalar Values
    NUMBER {
        @Override
        public ScalarInteger createNew(Lexeme value, List<SyntaxNode> args) {
            int number = Integer
                            .parseInt(
                                value
                                    .getValue()
                            );
            return new ScalarInteger(number);
        }
    },
    STRING,
    TRUE,
    FALSE,

    //operators
    PLUS {
        @Override
        public PlusInteger createNew(Lexeme value, List<SyntaxNode> args) {
            return new PlusInteger(args.get(0).fromNode(), args.get(1).fromNode());
        }
    },
    MINUS {
        @Override
        public MinusInteger createNew(Lexeme value, List<SyntaxNode> args) {
            return new MinusInteger(args.get(0).fromNode(), args.get(1).fromNode());
        }
    },
    DIV,
    MULT,
    ASSIGN,

    //Separators
    COMMA,
    LEFT_PARENTHESIS,
    RIGHT_PARENTHESIS,
    EOL,

    ID
    ;

    public IScalarType createNew(Lexeme value, List<SyntaxNode> args) {
        return null;
    }
}
