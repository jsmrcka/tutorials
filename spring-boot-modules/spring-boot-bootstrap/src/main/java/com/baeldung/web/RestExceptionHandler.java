package com.baeldung.web;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.baeldung.web.exception.BookIdMismatchException;
import com.baeldung.web.exception.BookNotFoundException;

import javax.persistence.PersistenceException;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<Object> handleNotFound(Exception ex) {
        return new ResponseEntity<>("Book not found", HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler({
            BookIdMismatchException.class,
            ConstraintViolationException.class,
            PersistenceException.class
    })
    public ResponseEntity<Object> handleBadRequest(Exception ex) {
        return new ResponseEntity<>(ex.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
    }
}