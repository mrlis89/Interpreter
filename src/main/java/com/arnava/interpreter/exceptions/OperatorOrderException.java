package com.arnava.interpreter.exceptions;

/**
 * This exception throws in case of operator format error
 */
public class OperatorOrderException extends SyntaxErrorException{
    public OperatorOrderException(String message) {
        super(message);
    }
}
