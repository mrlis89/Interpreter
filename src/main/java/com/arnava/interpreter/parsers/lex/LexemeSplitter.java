package com.arnava.interpreter.parsers.lex;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * {@code LexemeSplitter} class contains methods for splitting user entered line into String array
 */
public class LexemeSplitter implements ILexemeSplitter {
    private final Pattern pattern = Pattern.compile("([\\s\\*\\+\\-\\/\\=\\(\\)])|(\\d+)|(INT)|(STR)|([a-z]\\w+)|(\\\"([^\\\"]*)\\\")");

    /**
     * Method uses regular expression to split entered line into numbers, operators, names and other symbols
     * @param line user entered line
     * @return array of string elements
     */
    @Override
    public List<String> split(String line) {
        line = deleteSpacesIn(line);
        List<String> result = new ArrayList<>();
        Matcher matcher = pattern.matcher(line);
        while (matcher.find()) {
            result.add(
                    matcher.group()
            );
        }
        return result;
    }

    /**
     *
     * @param string user entered line
     * @return line without spaces
     */
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
