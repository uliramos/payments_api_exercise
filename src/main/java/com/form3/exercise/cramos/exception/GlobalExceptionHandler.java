package com.form3.exercise.cramos.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.form3.exercise.cramos.model.Payment;

@ControllerAdvice
public class GlobalExceptionHandler {

        @ExceptionHandler(RESTResourceNotFoundException.class)
        public ResponseEntity<Payment> handleResourceNotFound() {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

}