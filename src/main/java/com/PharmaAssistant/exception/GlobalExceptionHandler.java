package com.PharmaAssistant.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(PharmaBusinessException.class)
    public ResponseEntity<Object> handlePharmaBusinessException(PharmaBusinessException ex, WebRequest request) {
        logger.error("Business exception occurred: {}", ex.getMessage());

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("errorCode", ex.getErrorCode());
        body.put("message", ex.getMessage());

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PharmaException.class)
    public ResponseEntity<Object> handlePharmaException(PharmaException ex, WebRequest request) {
        logger.error("System exception occurred: {}", ex.getMessage());

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("errorCode", ex.getErrorCode());
        body.put("message", ex.getMessage());

        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
        logger.error("Unexpected exception occurred: {}", ex.getMessage());

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("errorCode", "500");
        body.put("message", "An unexpected error occurred");

        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
