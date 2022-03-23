package com.arnava.interpreter.parsers.lex;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LexemeSplitter implements ILexemeSplitter {
    private final Pattern pattern = Pattern.compile("([\\s\\*\\+\\-\\/\\=\\(\\)])|(\\d+)|(INT)|(STR)|([a-z]\\w+)|(\\\"([^\\\"]*)\\\")");

    @Override
    public List<String> split(String expression) {
        expression = deleteSpacesIn(expression);
        List<String> result = new ArrayList<>();
        Matcher matcher = pattern.matcher(expression);
        while (matcher.find()) {
            result.add(
                    matcher.group()
            );
        }
        return result;
    }

    private String deleteSpacesIn(String string) {
        String result = "";
        for (char sym : string.toCharArray()) {
            if (sym != ' ') {
                result += sym;
            }
        }
        return result;
    }
}
