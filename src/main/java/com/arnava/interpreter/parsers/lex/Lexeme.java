package com.arnava.interpreter.parsers.lex;

import java.util.Objects;

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

    public boolean isLowPriorOper () {
        return (type.equals(LexTypes.PLUS) || type.equals(LexTypes.MINUS));
    }

    public boolean isHighPriorOper () {
        return (type.equals(LexTypes.MULT) || type.equals(LexTypes.DIV));
    }

    public boolean isLeftBracket () {
        return type.equals(LexTypes.LEFT_PARENTHESIS);
    }

    public boolean isRightBracket () {
        return type.equals(LexTypes.RIGHT_PARENTHESIS);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lexeme lexeme = (Lexeme) o;
        return type == lexeme.type && Objects.equals(value, lexeme.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, value);
    }

    public boolean isVarType() {
        return type.equals(LexTypes.INTVAR);
    }
}
