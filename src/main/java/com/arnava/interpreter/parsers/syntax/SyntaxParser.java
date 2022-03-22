package com.arnava.interpreter.parsers.syntax;

import com.arnava.interpreter.parsers.lex.Lexeme;
import com.arnava.interpreter.vars.IVars;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SyntaxParser {

    private final ArrayList<IVars> Vars = new ArrayList<>();

    public SyntaxNode parseExpression(List<Lexeme> lexemes) {
        BranchSplitter branchSplitter = new BranchSplitter(lexemes);
        List<List<Lexeme>> branches = new ArrayList<List<Lexeme>>(2);
        ArrayList<Lexeme> leftBranch;
        ArrayList<Lexeme> rightBranch;
        Lexeme nodeParent;

        if (lexemes.size() == 1) {
            return new SyntaxNode(lexemes.get(0));
        } else {
            branches.addAll(branchSplitter.toNodeBranches());
            nodeParent = branchSplitter.getNodeParent();
            leftBranch = new ArrayList<>(branches.get(0));
            rightBranch = new ArrayList<>(branches.get(1));
        }

        if (leftBranch.size() == 1 && rightBranch.size() == 1) {
            return new SyntaxNode(nodeParent,
                    Arrays.asList(
                            new SyntaxNode(leftBranch.get(0)),
                            new SyntaxNode(rightBranch.get(0))
                    )
            );
        } else if (leftBranch.size() == 1) {
            return new SyntaxNode(nodeParent,
                    Arrays.asList(
                            new SyntaxNode(leftBranch.get(0)),
                            parseExpression(new ArrayList<>(rightBranch))
                    )
            );
        } else if (rightBranch.size() == 1) {
            return new SyntaxNode(nodeParent,
                    Arrays
                            .asList(this.parseExpression(new ArrayList<>(leftBranch)),
                                    new SyntaxNode(rightBranch.get(0))
                            )
            );
        } else {
            return new SyntaxNode(nodeParent,
                    Arrays
                            .asList(parseExpression(new ArrayList<>(leftBranch)),
                                    parseExpression(new ArrayList<>(rightBranch))
                            )
            );
        }
    }

    public void parse(List<Lexeme> lexemes) {
        Lexeme firstWord = lexemes.get(0);
        if (firstWord.isVarType()) {
            Lexeme varLexeme = lexemes.get(1);
            ArrayList<Lexeme> Expr =
                    new ArrayList<>(lexemes.subList(
                            3,
                            lexemes.size()
                    ));
            Vars.add((IVars) firstWord.getType().createNew(
                            varLexeme,
                            List.of(parseExpression(Expr))
                    )
            );
        }
    }

    public String printValueOf(String varName) {
        String ret = "";
        for (IVars var : Vars) {
            if (var.getName().equals(varName)) {
                ret = var.toScalar().toString();
            } else {
                ret = "VAR not found";
            }
        }
        return ret;
    }
}
