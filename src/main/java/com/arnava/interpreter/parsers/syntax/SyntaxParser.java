package com.arnava.interpreter.parsers.syntax;

import com.arnava.interpreter.parsers.lex.LexTypes;
import com.arnava.interpreter.parsers.lex.Lexeme;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SyntaxParser {

    public SyntaxNode toNodeTree(List<Lexeme> lexemes) {
        ArrayList<Lexeme> leftBranch = new ArrayList<>();
        ArrayList<Lexeme> rightBranch = new ArrayList<>();
        ArrayList<Lexeme> buffer = new ArrayList<>();
        Lexeme parent = new Lexeme(LexTypes.FALSE);

        if (lexemes.size() == 1) {
            return new SyntaxNode(lexemes.get(0));
        } else {
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
        }

        if (leftBranch.size() == 1 && rightBranch.size() == 1) {
            return new SyntaxNode(parent,
                    Arrays
                            .asList(new SyntaxNode(leftBranch.get(0)),
                                    new SyntaxNode(rightBranch.get(0))
                            )
            );
        } else if (leftBranch.size() == 1 && rightBranch.size() != 1) {
            return new SyntaxNode(parent,
                    Arrays
                            .asList(new SyntaxNode(leftBranch.get(0)),
                                    this.toNodeTree(new ArrayList<>(rightBranch))
                            )
            );
        } else if (leftBranch.size() != 1 && rightBranch.size() == 1) {
            return new SyntaxNode(parent,
                    Arrays
                            .asList(this.toNodeTree(new ArrayList<>(leftBranch)),
                                    new SyntaxNode(rightBranch.get(0))
                            )
            );
        } else {
            return new SyntaxNode(parent,
                    Arrays
                            .asList(this.toNodeTree(new ArrayList<>(leftBranch)),
                                    this.toNodeTree(new ArrayList<>(rightBranch))
                            )
            );
        }
    }
}
