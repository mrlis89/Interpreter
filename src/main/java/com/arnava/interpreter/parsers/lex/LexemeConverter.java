package com.arnava.interpreter.parsers.lex;

public class LexemeConverter implements ILexConverter {

    @Override
    public Lexeme fromString(String st) {
        Lexeme ret = null;
        String regex = "^\\d+$";
        if (st.matches(regex)) {
            ret = new Lexeme(LexTypes.NUMBER, st);
        } else {
            switch (st) {
                case "+": {
                    ret = new Lexeme(LexTypes.PLUS);
                    break;
                }
                case "-": {
                    ret = new Lexeme(LexTypes.MINUS);
                    break;
                }
                case "(": {
                    ret = new Lexeme(LexTypes.LEFT_PARENTHESIS);
                    break;
                }
                case ")": {
                    ret = new Lexeme(LexTypes.RIGHT_PARENTHESIS);
                    break;
                }
                case "*": {
                    ret = new Lexeme(LexTypes.MULT);
                    break;
                }
                case "/": {
                    ret = new Lexeme(LexTypes.DIV);
                    break;
                }
            }
        }
        return ret;
    }
}
