package com.simulate.accounting.exception;

public class AccountingServiceException extends RuntimeException {

    public AccountingServiceException(String message) {
        super(message);
    }

    public AccountingServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
