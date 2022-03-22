package com.arnava.interpreter.parsers.syntax;

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
    void splitForExpr() {
        List<List<Lexeme>> branches = new ArrayList<>(2);
        LexParser lp = new LexParser("5 -3 +7");
        BranchSplitter branchSplitter = new BranchSplitter(lp.parse());

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
    void splitForExprWithBrackets() {
        List<List<Lexeme>> branches = new ArrayList<>(2);
        LexParser lp = new LexParser("5 - (3 *7)");
        BranchSplitter branchSplitter = new BranchSplitter(lp.parse());

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
    void splitForHighPriorOperatorExpr() {
        List<List<Lexeme>> branches = new ArrayList<>(2);
        LexParser lp = new LexParser("5 / (3 *7)");
        BranchSplitter branchSplitter = new BranchSplitter(lp.parse());

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
    void getParent() {
        LexParser lp = new LexParser("5 -3 +7");
        BranchSplitter branchSplitter = new BranchSplitter(lp.parse());
        branchSplitter.toNodeBranches();

        assertThat(branchSplitter
                .getNodeParent()
        )
                .isEqualTo(
                        new Lexeme(LexTypes.PLUS)
                );
    }

    @Test
    void findBracketsIndexes() {
        int leftBracket;
        int rightBracket;
        LexParser lp = new LexParser("5 - (3 *7) + 1");
        BranchSplitter branchSplitter = new BranchSplitter(lp.parse());

        leftBracket = branchSplitter.findLeftBracketIndex();
        rightBracket = branchSplitter.findRightBracketIndex();
        assertThat(leftBracket).isEqualTo(2);
        assertThat(rightBracket).isEqualTo(6);
    }

    @Test
    void trimBrackets() {
        List<Lexeme> lexemes = new ArrayList<>();
        lexemes.addAll(new LexParser("(3 + 1)").parse());
        new BranchSplitter(lexemes)
                .trimBrackets(lexemes);
        assertThat(lexemes).isEqualTo(Arrays.asList(
                new Lexeme(LexTypes.NUMBER, "3"),
                new Lexeme(LexTypes.PLUS),
                new Lexeme(LexTypes.NUMBER, "1"))
        );
    }
}