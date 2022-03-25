package com.arnava.interpreter.exceptions;

/**
 * This exception throws in case of brackets count error
 */
public class BracketsCountException extends SyntaxErrorException{
    public BracketsCountException(String message) {
        super(message);
    }
}
