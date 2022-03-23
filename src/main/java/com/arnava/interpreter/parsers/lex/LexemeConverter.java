package com.arnava.interpreter.parsers.lex;

public class LexemeConverter implements ILexConverter {

    @Override
    public Lexeme toLexemeFrom(String string) {
        Lexeme ret = null;
        String numberRegex = "^\\d+$";
        String stringRegex = "\\\"([^\\\"]*)\\\"";
        String varRegex = "[a-z]\\w+";
        if (string.matches(numberRegex)) {
            ret = new Lexeme(LexTypes.NUMBER, string);
        } else if (string.matches(stringRegex)) {
            ret = new Lexeme(LexTypes.STRING, string);
        } else if (string.matches(varRegex)) {
            ret = new Lexeme(LexTypes.VARNAME, string);
        } else {
            switch (string) {
                case "+": {
                    ret = new Lexeme(LexTypes.PLUS);
                    break;
                }
                case "-": {
                    ret = new Lexeme(LexTypes.MINUS);
                    break;
                }
                case "(": {
                    ret = new Lexeme(LexTypes.LEFT_BRACKET);
                    break;
                }
                case ")": {
                    ret = new Lexeme(LexTypes.RIGHT_BRACKET);
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
                case "=": {
                    ret = new Lexeme(LexTypes.EQUALS);
                    break;
                }
                case "INT": {
                    ret = new Lexeme(LexTypes.INTVARTYPE);
                    break;
                }
                case "STR": {
                    ret = new Lexeme(LexTypes.STRVARTYPE);
                    break;
                }
            }
        }
        return ret;
    }
}
