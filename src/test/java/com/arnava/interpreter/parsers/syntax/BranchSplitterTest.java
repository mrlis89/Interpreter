package com.arnava.interpreter.parsers.syntax;

import com.arnava.interpreter.exceptions.BracketsCountException;
import com.arnava.interpreter.exceptions.OperatorOrderException;
import com.arnava.interpreter.parsers.lex.LexParser;
import com.arnava.interpreter.parsers.lex.LexTypes;
import com.arnava.interpreter.parsers.lex.Lexeme;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class BranchSplitterTest {

    @Test
    void splitForExpr() throws BracketsCountException, OperatorOrderException {
        List<List<Lexeme>> branches = new ArrayList<>(2);
        LexParser lp = new LexParser("5 -3 +7");
        BranchSplitter branchSplitter = new BranchSplitter(lp.toLexemeArray());

        branches = branchSplitter.toNodeBranches();

        assertThat(branches.get(0)).isEqualTo(Arrays.asList(
                        new Lexeme(LexTypes.NUMBER, "5"),
                        new Lexeme(LexTypes.MINUS),
                        new Lexeme(LexTypes.NUMBER, "3")
                )
        );

        assertThat(branches.get(1)).isEqualTo(Arrays.asList(
                        new Lexeme(LexTypes.NUMBER, "7")
                )
        );
    }

    @Test
    void splitForExprWithBrackets() throws BracketsCountException, OperatorOrderException {
        List<List<Lexeme>> branches = new ArrayList<>(2);
        LexParser lp = new LexParser("5 - (3 *7)");
        BranchSplitter branchSplitter = new BranchSplitter(lp.toLexemeArray());

        branches = branchSplitter.toNodeBranches();

        assertThat(branches.get(0)).isEqualTo(Arrays.asList(
                        new Lexeme(LexTypes.NUMBER, "5")
                )
        );

        assertThat(branches.get(1)).isEqualTo(Arrays.asList(
                        new Lexeme(LexTypes.NUMBER, "3"),
                        new Lexeme(LexTypes.MULT),
                        new Lexeme(LexTypes.NUMBER, "7")
                )
        );
    }

    @Test
    void splitForHighPriorOperatorExpr() throws BracketsCountException, OperatorOrderException {
        List<List<Lexeme>> branches = new ArrayList<>(2);
        LexParser lp = new LexParser("5 / (3 *7)");
        BranchSplitter branchSplitter = new BranchSplitter(lp.toLexemeArray());

        branches = branchSplitter.toNodeBranches();

        assertThat(branches.get(0)).isEqualTo(Arrays.asList(
                        new Lexeme(LexTypes.NUMBER, "5")
                )
        );

        assertThat(branches.get(1)).isEqualTo(Arrays.asList(
                        new Lexeme(LexTypes.NUMBER, "3"),
                        new Lexeme(LexTypes.MULT),
                        new Lexeme(LexTypes.NUMBER, "7")
                )
        );
    }

    @Test
    void getParent() throws BracketsCountException, OperatorOrderException {
        LexParser lp = new LexParser("5 -3 +7");
        BranchSplitter branchSplitter = new BranchSplitter(lp.toLexemeArray());
        branchSplitter.toNodeBranches();

        assertThat(branchSplitter
                .getNodeParent()
        )
                .isEqualTo(
                        new Lexeme(LexTypes.PLUS)
                );
    }

    @Test
    void findBracketsIndexes() throws BracketsCountException, OperatorOrderException {
        int leftBracket;
        int rightBracket;
        LexParser lp = new LexParser("5 - (3 *7) + 1");
        BranchSplitter branchSplitter = new BranchSplitter(lp.toLexemeArray());

        leftBracket = branchSplitter.findLeftBracketIndex();
        rightBracket = branchSplitter.findRightBracketIndex();
        assertThat(leftBracket).isEqualTo(2);
        assertThat(rightBracket).isEqualTo(6);
    }

    @Test
    void trimBrackets() throws BracketsCountException, OperatorOrderException {
        List<Lexeme> lexemes = new ArrayList<>();
        lexemes.addAll(new LexParser("(3 + 1)").toLexemeArray());
        new BranchSplitter(lexemes)
                .trimBrackets(lexemes);
        assertThat(lexemes).isEqualTo(Arrays.asList(
                new Lexeme(LexTypes.NUMBER, "3"),
                new Lexeme(LexTypes.PLUS),
                new Lexeme(LexTypes.NUMBER, "1"))
        );
    }
}