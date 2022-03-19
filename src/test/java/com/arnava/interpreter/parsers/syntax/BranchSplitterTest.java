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
        BranchSplitter branchSplitter = new BranchSplitter();

        LexParser lp = new LexParser("5 -3 +7");
        branches = branchSplitter.toBranches(lp.parse());

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
    void splitForExprWithParentheses() {
        List<List<Lexeme>> branches = new ArrayList<>(2);
        BranchSplitter branchSplitter = new BranchSplitter();

        LexParser lp = new LexParser("5 - (3 *7)");
        branches = branchSplitter.toBranches(lp.parse());

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
        BranchSplitter branchSplitter = new BranchSplitter();

        LexParser lp = new LexParser("5 / (3 *7)");
        branches = branchSplitter.toBranches(lp.parse());

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
        BranchSplitter branchSplitter = new BranchSplitter();
        LexParser lp = new LexParser("5 -3 +7");
        branchSplitter.toBranches(lp.parse());

        assertThat(branchSplitter
                .getParent()
        )
                .isEqualTo(
                        new Lexeme(LexTypes.PLUS)
                );
    }

    @Test
    void findParenthesesIndexes() {
        int[] res = new int[2];
        BranchSplitter branchSplitter = new BranchSplitter();
        LexParser lp = new LexParser("5 - (3 *7) + 1");

        res = branchSplitter.findIndexes(lp.parse());
        assertThat(res).containsExactly(2, 6);
    }

    @Test
    void trimParentheses() {
        List<Lexeme> lexemes = new ArrayList<>();
        lexemes.addAll(new LexParser("(3 + 1)").parse());
        new BranchSplitter()
                .trimParentheses(lexemes);
        assertThat(lexemes).isEqualTo(Arrays.asList(
                new Lexeme(LexTypes.NUMBER, "3"),
                new Lexeme(LexTypes.PLUS),
                new Lexeme(LexTypes.NUMBER, "1"))
        );
    }
}