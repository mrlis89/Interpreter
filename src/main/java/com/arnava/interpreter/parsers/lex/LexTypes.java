package com.arnava.interpreter.parsers.lex;

public enum LexTypes {
    //Scalar Values
    NUMBER,
    STRING,
    TRUE,
    FALSE,

    //operators
    PLUS,
    MINUS,
    DIV,
    MULT,
    ASSIGN,

    //Separators
    COMMA,
    LEFT_PARENTHESIS,
    RIGHT_PARENTHESIS,
    EOL,

    ID,
}
