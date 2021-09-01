package com.arnava.interpreter.parsers.lex;

public class LexemeConverter implements ILexConverter{

    @Override
    public Lexeme fromString(String st) {
        String regex = "^\\d+$";
        if (st.matches(regex)) {
            return new Lexeme(LexTypes.NUMBER, st);
        }

        switch (st){
            case "+" : return new Lexeme(LexTypes.PLUS);
            case "-" : return new Lexeme(LexTypes.MINUS);
        }
        return null;
    }
}
