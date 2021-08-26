package com.arnava.interpreter.parsers.lex;

public class LexemeConverter implements ILexConverter{

    @Override
    public Lexeme fromString(String st) {
        Lexeme ret = null;
        String regex = "^\\d+$";
        if (st.matches(regex)) {
            ret = new Lexeme(LexTypes.NUMBER, st);
        }

        switch (st){
            case "+" : {
                ret = new Lexeme(LexTypes.PLUS);
                break;
            }
            case "-" : {
                ret = new Lexeme(LexTypes.MINUS);
                break;
            }
        }
        return ret;
    }
}
