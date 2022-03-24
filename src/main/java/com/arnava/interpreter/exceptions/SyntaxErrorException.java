package com.arnava.interpreter.exceptions;

/**
 * Base class for syntax exception
 */
public class SyntaxErrorException extends Exception{
    public SyntaxErrorException(String message) {
        super(message);
    }
}
