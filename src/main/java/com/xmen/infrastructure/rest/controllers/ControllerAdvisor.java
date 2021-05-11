package com.xmen.infrastructure.rest.controllers;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Handle to manage error response when exception are raised
 *
 * @author <a href="davidsandoval9217@gmail.com"> David Sandoval</a>
 * @since 1.0
 * @version 1.0
 */
@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ControllerAdvisor {

    /**
     * Handles exception
     * @return Error with 403 HTTP status
     */
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Void> handleValidationException(final RuntimeException ex){
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }
}
