package com.solomka.springsecurity.exceptions.hahdlers;

import com.solomka.springsecurity.exceptions.BadIdException;
import com.solomka.springsecurity.exceptions.BookNameIsNullException;
import com.solomka.springsecurity.exceptions.BookNameIsTooLongException;
import com.solomka.springsecurity.exceptions.InvalidTokenException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ErrorMessage> handleMovieNotFoundException(Exception ex) {
        ErrorMessage errors = new ErrorMessage();
        errors.setTimestamp(LocalDateTime.now());
        errors.setError(ex.getCause().getMessage());
        errors.setStatus(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(errors, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {
            IllegalArgumentException.class,
            InvalidTokenException.class,
            BadIdException.class,
            BookNameIsNullException.class,
            BookNameIsTooLongException.class,})
    public ResponseEntity<ErrorMessage> handleIllegalArgumentException(Exception ex) {
        ErrorMessage errors = new ErrorMessage();
        errors.setTimestamp(LocalDateTime.now());
        errors.setError(ex.getMessage());
        errors.setStatus(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ErrorMessage> handleAuthenticationException(Exception ex) {
        ErrorMessage errors = new ErrorMessage();
        errors.setTimestamp(LocalDateTime.now());
        errors.setError(ex.getMessage());
        errors.setStatus(HttpStatus.FORBIDDEN);
        return new ResponseEntity<>(errors, HttpStatus.FORBIDDEN);
    }
}
