package com.PharmaAssistant.exception;

public class PharmaBusinessException extends RuntimeException {
    private String errorCode;

    public PharmaBusinessException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }
}