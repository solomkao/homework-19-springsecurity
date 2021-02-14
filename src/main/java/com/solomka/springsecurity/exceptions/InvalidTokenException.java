package com.solomka.springsecurity.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;

public class InvalidTokenException extends AuthenticationException {
    private HttpStatus httpStatus;
    private final static String message = "JWT Token is expired or invalid";
    public InvalidTokenException(HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public InvalidTokenException(){
        super(message);
    }


}
