package com.arnava.interpreter.parsers.lex;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LexemeSplitter implements ILexemeSplitter {
    private final Pattern pattern = Pattern.compile("([\\s\\*\\+\\-\\/\\=\\(\\)])|(\\d+)|(INT)|(STR)|([a-z]\\w+)|(\\\"([^\\\"]*)\\\")");

    @Override
    public List<String> split(String expression) {
        expression = deleteSpaces(expression);
        List<String> result = new ArrayList<>();
        Matcher matcher = pattern.matcher(expression);
        while (matcher.find()) {
            result.add(
                    matcher.group()
            );
        }
        return result;
    }

    private String deleteSpaces(String st) {
        String result = "";
        for (char sym : st.toCharArray()) {
            if (sym != ' ') {
                result += sym;
            }
        }
        return result;
    }
}
