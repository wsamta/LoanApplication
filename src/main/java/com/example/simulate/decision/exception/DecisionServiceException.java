package com.simulate.decision.exception;

public class DecisionServiceException extends RuntimeException {
    public DecisionServiceException(String message) {
        super(message);
    }

    public DecisionServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
