package com.arnava.interpreter.parsers.syntax;

import com.arnava.interpreter.parsers.lex.Lexeme;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SyntaxParser {
    private ArrayList<Lexeme> leftBranch = new ArrayList<Lexeme>();
    private ArrayList<Lexeme> rightBranch = new ArrayList<Lexeme>();
    private ArrayList<Lexeme> buffer = new ArrayList<Lexeme>();
    private Lexeme parent;


    public SyntaxNode toNodeTree(List<Lexeme> lexemes) {
        leftBranch.clear();
        rightBranch.clear();
        parseLine(lexemes);
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

    public void parseLine(List<Lexeme> lexemes) {
        if (lexemes.size() == 1) {
            parent = lexemes.get(0);
        } else {
            for (int i = 0; i < lexemes.size(); i++) {
                Lexeme lex = lexemes.get(i);
                String lexType = lex.getType().name();
                if (lexType.equals("PLUS") | lexType.equals("MINUS")) {
                    this.parent = lex;
                    this.leftBranch.addAll(buffer);
                    buffer.clear();
                    for (int j = i + 1; j < lexemes.size(); j++) {
                        buffer.add(lexemes.get(j));
                    }
                    this.rightBranch.addAll(buffer);
                    buffer.clear();
                    break;
                }
                buffer.add(lex);
            }
        }
    }
}
