package com.arnava.interpreter.parsers.syntax;

import com.arnava.interpreter.parsers.lex.LexTypes;
import com.arnava.interpreter.parsers.lex.Lexeme;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SyntaxParser {

    private BranchSplitter branchSplitter = new BranchSplitter();

    public SyntaxNode toNodeTree(List<Lexeme> lexemes) {
        List<List<Lexeme>> branches = new ArrayList<List<Lexeme>>(2);
        ArrayList<Lexeme> leftBranch = new ArrayList<>();
        ArrayList<Lexeme> rightBranch = new ArrayList<>();
        Lexeme parent;

        if (lexemes.size() == 1) {
            return new SyntaxNode(lexemes.get(0));
        } else {
            branches.addAll(branchSplitter.split(lexemes));
            leftBranch.addAll(branches.get(0));
            rightBranch.addAll(branches.get(1));
            parent = branchSplitter.getParent();
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
