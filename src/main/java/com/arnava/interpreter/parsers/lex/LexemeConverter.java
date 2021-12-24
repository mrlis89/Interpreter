package com.arnava.interpreter.parsers.lex;

public class LexemeConverter implements ILexConverter {

    @Override
    public Lexeme fromString(String st) {
        Lexeme ret = null;
        String numberRegex = "^\\d+$";
        String varRegex = "[a-z]\\w+";
        if (st.matches(numberRegex)) {
            ret = new Lexeme(LexTypes.NUMBER, st);
        } else if (st.matches(varRegex)) {
            ret = new Lexeme(LexTypes.VARNAME, st);
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
                case "INT": {
                    ret = new Lexeme(LexTypes.INTVAR);
                    break;
                }
                case "STR": {
                    ret = new Lexeme(LexTypes.STRVAR);
                    break;
                }
            }
        }
        return ret;
    }
}
