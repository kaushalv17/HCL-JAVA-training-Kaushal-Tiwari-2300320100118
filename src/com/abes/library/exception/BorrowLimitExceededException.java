package com.abes.library.exception;

public class BorrowLimitExceededException extends LibraryException {
    public BorrowLimitExceededException(String message) { super(message); }
}

