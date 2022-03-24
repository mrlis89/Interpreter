package com.arnava.interpreter.parsers.syntax;

import com.arnava.interpreter.parsers.lex.Lexeme;

import java.util.List;

public interface IBranchSplitter {
    List<List<Lexeme>> toLexemeBranches();
}
