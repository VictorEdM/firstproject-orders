package com.firstproject.orders.controllers.exceptions;

import com.firstproject.orders.services.exceptions.DatabaseException;
import com.firstproject.orders.services.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;

@ControllerAdvice
public class ResourceExceptionHandler{

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException exception,
                                                          HttpServletRequest request) {
        String errorType = "Resource not found";
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        StandardError standardError = new StandardError(
                Instant.now(),
                httpStatus.value(),
                errorType,
                exception.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(httpStatus).body(standardError);
    }

    @ExceptionHandler(DatabaseException.class)
    public ResponseEntity<StandardError> database(DatabaseException exception,
                                                      HttpServletRequest request) {
        String errorType = "Database error";
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        StandardError standardError = new StandardError(
                Instant.now(),
                httpStatus.value(),
                errorType,
                exception.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(httpStatus).body(standardError);
    }



}
