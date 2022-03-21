package com.arnava.interpreter.parsers.lex;

import java.util.ArrayList;
import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LexemeSplitter implements ILexemeSplitter {
    private Pattern pattern = Pattern.compile("([\\s\\*\\+\\-\\/\\=\\(\\)])|(\\d+)|(INT)|(STR)|([a-z]\\w+)|(\\\"([^\\\"]*)\\\")");
    private Matcher match;

    @Override
    public Collection<String> split(String st) {
        st = deleteSpaces(st);
        Collection<String> result = new ArrayList<>();
        match = pattern.matcher(st);
            while (match.find()) {
                result.add(
                        match.group()
                );
            }
        return result;
    }

    private String deleteSpaces(String st) {
        String result = "";
        for (char sym: st.toCharArray()) {
            if (sym != ' ') {
                result += sym;
            }
        }
        return result;
    }
}
