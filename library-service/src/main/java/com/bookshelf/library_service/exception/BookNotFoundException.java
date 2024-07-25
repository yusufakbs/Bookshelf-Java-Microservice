package com.bookshelf.library_service.exception;

public class BookNotFoundException extends RuntimeException {
    private ExceptionMessage exceptionMessage;

    public BookNotFoundException(String message) {
        super(message);
    }

    public BookNotFoundException(ExceptionMessage exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }

    public BookNotFoundException(String message, ExceptionMessage exceptionMessage) {
        super(message);
        this.exceptionMessage = exceptionMessage;
    }

    public ExceptionMessage getExceptionMessage() {
        return exceptionMessage;
    }

}
