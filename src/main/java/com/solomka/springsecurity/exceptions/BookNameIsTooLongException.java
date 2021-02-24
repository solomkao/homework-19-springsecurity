package com.solomka.springsecurity.exceptions;

public class BookNameIsTooLongException extends RuntimeException {

    private static final String message = "Book's name is too long.";

    public BookNameIsTooLongException() {
        super(message);
    }
}
