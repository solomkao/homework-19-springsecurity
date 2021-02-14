package com.solomka.springsecurity.exceptions;

public class BadIdException extends RuntimeException {

    private static final String message = "Id is invalid.";

    public BadIdException() {
        super(message);
    }
}
