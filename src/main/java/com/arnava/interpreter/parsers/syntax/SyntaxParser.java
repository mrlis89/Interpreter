package com.arnava.interpreter.parsers.syntax;

import com.arnava.interpreter.parsers.lex.Lexeme;
import com.arnava.interpreter.vars.IVars;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SyntaxParser {

    private BranchSplitter branchSplitter = new BranchSplitter();
    private ArrayList<IVars> Vars = new ArrayList<>();

    public void parse(List<Lexeme> lexemes) {
        Lexeme firstWord = lexemes.get(0);
        if (firstWord.isVarType()) {
            ArrayList<Lexeme> Expr = new ArrayList<>();
            Lexeme varLexeme = lexemes.get(1);
            Expr
                    .addAll(
                            lexemes
                                    .subList(
                                            3,
                                            lexemes.size()
                                    )
                    );
            Vars
                    .add(
                            (IVars) firstWord
                                    .getType()
                                    .createNew(
                                            varLexeme,
                                            List.of(parseExpression(Expr))
                                            )
                    );
        }
    }

    public String printValueOf(String varName){
        String ret = "";
        for (IVars var: Vars) {
            if (var.getName().equals(varName)) {
                ret = var.toScalar().toString();
            } else {
                ret = "VAR not found";
            }
        }
        return ret;
    }

    public SyntaxNode parseExpression(List<Lexeme> lexemes) {
        List<List<Lexeme>> branches = new ArrayList<List<Lexeme>>(2);
        ArrayList<Lexeme> leftBranch = new ArrayList<>();
        ArrayList<Lexeme> rightBranch = new ArrayList<>();
        Lexeme parent;

        if (lexemes.size() == 1) {
            return new SyntaxNode(lexemes.get(0));
        } else {
            branches.addAll(branchSplitter.toBranches(lexemes));
            parent = branchSplitter.getParent();
            leftBranch.addAll(branches.get(0));
            rightBranch.addAll(branches.get(1));
        }

        if (leftBranch.size() == 1 && rightBranch.size() == 1) {
            return new SyntaxNode(parent,
                    Arrays
                            .asList(new SyntaxNode(leftBranch.get(0)),
                                    new SyntaxNode(rightBranch.get(0))
                            )
            );
        } else if (leftBranch.size() == 1) {
            return new SyntaxNode(parent,
                    Arrays
                            .asList(new SyntaxNode(leftBranch.get(0)),
                                    this.parseExpression(new ArrayList<>(rightBranch))
                            )
            );
        } else if (rightBranch.size() == 1) {
            return new SyntaxNode(parent,
                    Arrays
                            .asList(this.parseExpression(new ArrayList<>(leftBranch)),
                                    new SyntaxNode(rightBranch.get(0))
                            )
            );
        } else {
            return new SyntaxNode(parent,
                    Arrays
                            .asList(this.parseExpression(new ArrayList<>(leftBranch)),
                                    this.parseExpression(new ArrayList<>(rightBranch))
                            )
            );
        }
    }
}
