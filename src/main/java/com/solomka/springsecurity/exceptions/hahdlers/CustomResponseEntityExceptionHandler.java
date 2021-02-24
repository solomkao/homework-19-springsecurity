package com.solomka.springsecurity.exceptions.hahdlers;

import com.solomka.springsecurity.exceptions.BadIdException;
import com.solomka.springsecurity.exceptions.BookNameIsNullException;
import com.solomka.springsecurity.exceptions.BookNameIsTooLongException;
import com.solomka.springsecurity.exceptions.InvalidTokenException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ErrorMessage error = ErrorMessage.createErrorMessage(ex, HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = UsernameNotFoundException.class)
    public ResponseEntity<ErrorMessage> handleUserNotFoundException(UsernameNotFoundException ex) {
        ErrorMessage error = ErrorMessage.createErrorMessage(ex, HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = BadIdException.class)
    public ResponseEntity<ErrorMessage> handleBadIdException(BadIdException ex) {
        ErrorMessage error = ErrorMessage.createErrorMessage(ex, HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {
            BookNameIsNullException.class,
            BookNameIsTooLongException.class,
            InvalidTokenException.class
    })
    public ResponseEntity<ErrorMessage> handleInvalidDataException(Exception ex) {
        ErrorMessage error = ErrorMessage.createErrorMessage(ex, HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ErrorMessage> handleAuthenticationException(AuthenticationException ex) {
        ErrorMessage error = ErrorMessage.createErrorMessage(ex, HttpStatus.FORBIDDEN);
        return new ResponseEntity<>(error, HttpStatus.FORBIDDEN);
    }
}
