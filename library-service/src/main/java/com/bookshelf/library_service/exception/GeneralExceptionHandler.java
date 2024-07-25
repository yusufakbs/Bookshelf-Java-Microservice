package com.bookshelf.library_service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GeneralExceptionHandler {

    @ExceptionHandler(LibraryNotFoundException.class)
    public ResponseEntity<?> handleLibraryNotFoundException(LibraryNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<ExceptionMessage> handleBookNotFoundException(BookNotFoundException e) {
        return new ResponseEntity<ExceptionMessage>(e.getExceptionMessage(), HttpStatus.resolve(e.getExceptionMessage().status()));
    }

}
