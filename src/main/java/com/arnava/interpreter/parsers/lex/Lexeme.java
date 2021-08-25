package com.arnava.interpreter.parsers.lex;

import java.util.Objects;

public class Lexeme {
    private final String value;
    private final LexTypes type;

    public Lexeme(LexTypes type) {
        this.type = type;
        this.value = "";
    }

    public Lexeme( LexTypes type, String value) {
        this.value = value;
        this.type = type;
    }

    public LexTypes getType() {
        return type;
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object otherObj) {
        if (this == otherObj) return true;
        if (otherObj == null || getClass() != otherObj.getClass()) return false;
        Lexeme lexeme = (Lexeme) otherObj;
        return Objects.equals(value, lexeme.value) && type == lexeme.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, type);
    }
}
