package com.solomka.springsecurity.exceptions;

public class BookNameIsNullException extends RuntimeException {

    private static final String message = "Book's name is null.";

    public BookNameIsNullException() {
        super(message);
    }
}
