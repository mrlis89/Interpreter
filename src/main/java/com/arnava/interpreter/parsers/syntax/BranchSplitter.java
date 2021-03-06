package com.arnava.interpreter.parsers.syntax;

import com.arnava.interpreter.parsers.lex.LexTypes;
import com.arnava.interpreter.parsers.lex.Lexeme;

import java.util.ArrayList;
import java.util.List;

/**
 * This class should to convert user entered line into tree of Lexemes and pass this tree to {@code SyntaxParser}
 * to be converted into a tree of syntax nodes
 * @see SyntaxNode
 */
public class BranchSplitter implements IBranchSplitter {
    private Lexeme NodeParent;
    private final List<Lexeme> lexemes;
    private final ArrayList<Lexeme> leftBranch = new ArrayList<>();
    private final ArrayList<Lexeme> rightBranch = new ArrayList<>();
    int leftBracketIndex = 0;
    int rightBracketIndex = 0;
    boolean lowPriorOperatorFound = false;

    public BranchSplitter(List<Lexeme> lexemes) {
        this.lexemes = lexemes;
    }

    /**
     *The algorithm divides Lexeme array by found operator. First, we look for an operator
     *      with a low priority, if we do not find it, then we try to find an operator with a high priority.
     *      If the expression contains brackets then the operator must be out of them.
     *      The array will be divided into two branches by the index of the found operator.
     * @return the array which contains left and right part of given {@code Lexeme} array.
     *
     */
    public List<List<Lexeme>> toLexemeBranches() {
        List<List<Lexeme>> result = new ArrayList<>(2);

        if (lexemes.contains(new Lexeme(LexTypes.LEFT_BRACKET))) {
            leftBracketIndex = findLeftBracketIndex();
            rightBracketIndex = findRightBracketIndex();
        }
//looking for low priority operator
        int lowPriorOperatorIndex = findLowPriorOperatorIndex();
        if (lowPriorOperatorIndex != 0) {
            splitExpressionByOperatorIndex(lowPriorOperatorIndex);
        }
//looking for high priority operator
        if (!lowPriorOperatorFound) {
            splitExpressionByOperatorIndex(findHighPriorOperatorIndex());
        }

        trimBrackets(leftBranch);
        trimBrackets(rightBranch);
        result.add(leftBranch);
        result.add(rightBranch);
        return result;
    }

    public int findLowPriorOperatorIndex() {
        int result = 0;
        for (int i = lexemes.size() - 1; i > 0; i--) {
            Lexeme lexeme = lexemes.get(i);
            if (lexeme.isLowPriorOperator() && (i < leftBracketIndex || i > rightBracketIndex)) {
                result = i;
                lowPriorOperatorFound = true;
                break;
            }
        }
        return result;
    }

    public int findHighPriorOperatorIndex() {
        int result = 0;
        for (int i = lexemes.size() - 1; i > 0; i--) {
            Lexeme lexeme = lexemes.get(i);
            if (lexeme.isHighPriorOper() && (i < leftBracketIndex || i > rightBracketIndex)) {
                result = i;
                break;
            }
        }
        return result;
    }

    public void splitExpressionByOperatorIndex(int index) {
        NodeParent = lexemes.get(index);
        for (int j = 0; j < index; j++) {
            leftBranch.add(lexemes.get(j));
        }
        for (int j = index + 1; j < lexemes.size(); j++) {
            rightBranch.add(lexemes.get(j));
        }
    }

    public int findLeftBracketIndex() {
        int leftBracketIndex = 0;
        for (int i = 0; i < lexemes.size(); i++) {
            if (lexemes.get(i).isLeftBracket()) {
                leftBracketIndex = i;
            }
        }
        return leftBracketIndex;
    }

    public int findRightBracketIndex() {
        int rightBracketIndex = 0;
        for (int i = lexemes.size() - 1; i >= 0; i--) {
            if (lexemes.get(i).isRightBracket()) {
                rightBracketIndex = i;
            }
        }
        return rightBracketIndex;
    }

    public Lexeme getNodeParent() {
        return NodeParent;
    }

    public void trimBrackets(List<Lexeme> lexemes) {
        if (lexemes.get(0).isLeftBracket() && lexemes.get(lexemes.size() - 1).isRightBracket()) {
            lexemes.remove(lexemes.size() - 1);
            lexemes.remove(0);
        }
    }
}
