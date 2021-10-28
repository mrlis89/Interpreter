package com.arnava.interpreter.parsers.syntax;

import com.arnava.interpreter.parsers.lex.LexTypes;
import com.arnava.interpreter.parsers.lex.Lexeme;

import java.util.ArrayList;
import java.util.List;

public class BranchSplitter implements IBranchSplitter {
    private Lexeme parent;

    public List<List<Lexeme>> toBranches(List<Lexeme> lexemes) {
        ArrayList<Lexeme> leftBranch = new ArrayList<>();
        ArrayList<Lexeme> rightBranch = new ArrayList<>();
        ArrayList<Lexeme> buffer = new ArrayList<>();
        List<List<Lexeme>> result = new ArrayList<>(2);
        int[] parenthesesIndexes = new int[2];

        if (lexemes
                .contains(
                        new Lexeme(
                                LexTypes.LEFT_PARENTHESIS)
                )
        ) {
            parenthesesIndexes = findIndexes(lexemes);
        }
//looking for low priority operator
        for (int i = 0; i < lexemes.size(); i++) {
            Lexeme lex = lexemes.get(i);
            if (lex.isLowPriorOper() && (i < parenthesesIndexes[0] || i > parenthesesIndexes[1])) {
                parent = lex;
                leftBranch.addAll(buffer);
                buffer.clear();
                for (int j = i + 1; j < lexemes.size(); j++) {
                    buffer.add(lexemes.get(j));
                }
                rightBranch.addAll(buffer);
                buffer.clear();
                break;
            }
            buffer.add(lex);
        }
//looking for high priority operator
        buffer.clear();
        for (int i = 0; i < lexemes.size(); i++) {
            Lexeme lex = lexemes.get(i);
            if (lex.isHighPriorOper() && (i < parenthesesIndexes[0] || i > parenthesesIndexes[1])) {
                parent = lex;
                leftBranch.addAll(buffer);
                buffer.clear();
                for (int j = i + 1; j < lexemes.size(); j++) {
                    buffer.add(lexemes.get(j));
                }
                rightBranch.addAll(buffer);
                buffer.clear();
                break;
            }
            buffer.add(lex);
        }

        trimParentheses(leftBranch);
        trimParentheses(rightBranch);
        result.add(leftBranch);
        result.add(rightBranch);
        return result;
    }

    public int[] findIndexes(List<Lexeme> lexemes) {
        int[] result = new int[2];
        for (int i = 0; i < lexemes.size(); i++) {
            if (lexemes.get(i).isLeftBracket()) {
                result[0] = i;
                break;
            }
        }
        for (int i = lexemes.size()-1; i >= 0; i--) {
            if (lexemes.get(i).isRightBracket()) {
                result[1] = i;
                break;
            }
        }
        return result;
    }

    public Lexeme getParent() {
        return parent;
    }

    public void trimParentheses(List<Lexeme> lexemes) {
        if (lexemes.get(0).isLeftBracket() && lexemes.get(lexemes.size()-1).isRightBracket()) {
            lexemes.remove(lexemes.size()-1);
            lexemes.remove(0);
        }
    }
}