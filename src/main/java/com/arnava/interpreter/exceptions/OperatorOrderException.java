package com.arnava.interpreter.exceptions;

public class OperatorOrderException extends SyntaxErrorException{
    public OperatorOrderException(String message) {
        super(message);
    }
}
