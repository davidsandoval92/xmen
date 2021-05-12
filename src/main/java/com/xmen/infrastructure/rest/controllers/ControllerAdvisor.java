package com.xmen.infrastructure.rest.controllers;

import com.xmen.domain.exceptions.InvalidDnaException;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ControllerAdvisor {

    /**
     * Handles exception
     * @return Error with 403 HTTP status
     */
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Void> handleRuntimeException(final RuntimeException ex){
        log.info("message error, {message:{}} ",ex.getMessage());
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    /**
     * Handles exception
     * @return Error with 403 HTTP status
     */
    @ExceptionHandler(InvalidDnaException.class)
    public ResponseEntity<Void> handleInvalidDnaException(final InvalidDnaException ex){
        log.info("Invalid DNA error, {message:{}} ",ex.getMessage());
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }
}
