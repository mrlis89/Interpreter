package com.arnava.interpreter.parsers.syntax;

import com.arnava.interpreter.parsers.lex.Lexeme;

import java.util.ArrayList;
import java.util.List;

public class BranchSplitter {
    private Lexeme parent;

    public List<List<Lexeme>> split(List<Lexeme> lexemes) {
        ArrayList<Lexeme> leftBranch = new ArrayList<>();
        ArrayList<Lexeme> rightBranch = new ArrayList<>();
        ArrayList<Lexeme> buffer = new ArrayList<>();
        List<List<Lexeme>> result = new ArrayList<List<Lexeme>>(2);

        for (int i = 0; i < lexemes.size(); i++) {
            Lexeme lex = lexemes.get(i);
            if (lex.isLowPriorOper()) {
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
        result.add(leftBranch);
        result.add(rightBranch);
        return result;
    }

    public Lexeme getParent() {
        return parent;
    }
}
