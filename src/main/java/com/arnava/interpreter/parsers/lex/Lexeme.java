package com.arnava.interpreter.parsers.lex;

public class Lexeme {
    private final LexTypes type;
    private final String value;

    public Lexeme(LexTypes type) {
        this.type = type;
        this.value = "";
    }

    public Lexeme(LexTypes type, String value) {
        this.value = value;
        this.type = type;
    }

    public LexTypes getType() {
        return type;
    }

    public String getValue() {
        return value;
    }

}
