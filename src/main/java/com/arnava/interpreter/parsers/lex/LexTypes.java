package com.arnava.interpreter.parsers.lex;

import com.arnava.interpreter.operators.DivInteger;
import com.arnava.interpreter.operators.MinusInteger;
import com.arnava.interpreter.operators.MultInteger;
import com.arnava.interpreter.operators.PlusInteger;
import com.arnava.interpreter.parsers.syntax.SyntaxNode;
import com.arnava.interpreter.scalars.ScalarInteger;
import com.arnava.interpreter.scalars.ScalarString;
import com.arnava.interpreter.types.IScalarType;
import com.arnava.interpreter.vars.IntVar;
import com.arnava.interpreter.vars.StrVar;

import java.util.List;

/**
 * Class contains types of {@code Lexemes}
 * @see Lexeme
 */
public enum LexTypes {
    //Scalar Values
    NUMBER {
        @Override
        public ScalarInteger createNew(Lexeme lex, List<SyntaxNode> args) {
            int number = Integer.parseInt(
                    lex.getValue()
            );
            return new ScalarInteger(number);
        }
    },
    STRING {
        @Override
        public ScalarString createNew(Lexeme lex, List<SyntaxNode> args) {
            String text = lex.getValue();
            return new ScalarString(
                    text.substring(
                            1,
                            text.length() - 1
                    )
            );
        }
    },

    //Var Types
    INTVARTYPE {
        @Override
        public IntVar createNew(Lexeme name, List<SyntaxNode> args) {
            return new IntVar(name.getValue(), args.get(0));
        }
    },
    STRVARTYPE {
        @Override
        public StrVar createNew(Lexeme name, List<SyntaxNode> args) {
            return new StrVar(name.getValue(), args.get(0));
        }
    },

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
    DIV {
        @Override
        public DivInteger createNew(Lexeme value, List<SyntaxNode> args) {
            return new DivInteger(args.get(0).fromNode(), args.get(1).fromNode());
        }
    },
    MULT {
        @Override
        public MultInteger createNew(Lexeme value, List<SyntaxNode> args) {
            return new MultInteger(args.get(0).fromNode(), args.get(1).fromNode());
        }
    },
    VARNAME,
    EQUALS,

    //Separators
    LEFT_BRACKET,
    RIGHT_BRACKET;

    /**
     * Method creates new object using creation context of Lexeme type. Every Lexeme type overrides this method to create needed object
     * @param value Lexeme which represents type of Syntax Node
     * @param args array of child Syntax Nodes
     * @see SyntaxNode
     */
    public IScalarType createNew(Lexeme value, List<SyntaxNode> args) {
        System.out.println("operator not overridden");
        return null;
    }
}
