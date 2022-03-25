package com.arnava.interpreter.parsers.lex;

import java.util.Objects;

/**
 * {@code Lexeme} is a lexical unit which contains two fields: {@code type} of Lexeme and its String {@code value }
 * @see LexTypes
 */
public class Lexeme {
    private final LexTypes type;
    private final String value;

    /**
     * This constructor initializes new {@code Lexeme} which has no value, such as bracket or math operator.
     */
    public Lexeme(LexTypes type) {
        this.type = type;
        this.value = "";
    }
    /**
     * This constructor initializes new {@code Lexeme} which has {@code value}, used for numbers.
     */
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

    /**
     * Method checks that {@code Lexeme} has operator type
     */
    public boolean isOperator() {
        return (isLowPriorOperator() || isHighPriorOper());
    }
    /**
     * Method checks that {@code Lexeme} has low priority operator type, such as Plus and Minus
     */
    public boolean isLowPriorOperator() {
        return (type.equals(LexTypes.PLUS) || type.equals(LexTypes.MINUS));
    }
    /**
     * Method checks that {@code Lexeme} has high priority operator type, such as Division and Multiplication
     */
    public boolean isHighPriorOper () {
        return (type.equals(LexTypes.MULT) || type.equals(LexTypes.DIV));
    }
    /**
     * Method checks that {@code Lexeme} has left bracket type
     */
    public boolean isLeftBracket () {
        return type.equals(LexTypes.LEFT_BRACKET);
    }
    /**
     * Method checks that {@code Lexeme} has right bracket type
     */
    public boolean isRightBracket () {
        return type.equals(LexTypes.RIGHT_BRACKET);
    }
    /**
     * Method checks that {@code Lexeme} has variable type
     */
    public boolean isVarType() {
        return type.equals(LexTypes.INTVARTYPE) || type.equals(LexTypes.STRVARTYPE);
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
}
