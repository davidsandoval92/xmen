package com.xmen.infrastructure.rest.controllers;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ControllerAdvisor {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Void> handleValidationException(){
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }
}
