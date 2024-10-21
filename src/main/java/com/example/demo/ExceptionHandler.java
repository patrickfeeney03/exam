package com.example.demo;

import com.example.demo.Exceptions.*;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ExceptionHandler {
    @org.springframework.web.bind.annotation.ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleCustomerNotFound(CustomerNotFoundException ex) {
        ErrorResponse response = new ErrorResponse();
        response.setTimestamp(String.valueOf(LocalDateTime.now()));
        response.setStatus(HttpStatus.NOT_FOUND.value());
        response.setMessage("Customer with customerId " + ex.getId() + " not found");
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(DuplicateCustomerIdException.class)
    public ResponseEntity<ErrorResponse> handleDuplicateCustomerIdException(DuplicateCustomerIdException ex) {
        ErrorResponse response = new ErrorResponse();
        response.setTimestamp(String.valueOf(LocalDateTime.now()));
        response.setStatus(HttpStatus.CONFLICT.value());
        response.setMessage("Customer with customerId " + ex.getId() + " already exists");
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

//    @org.springframework.web.bind.annotation.ExceptionHandler(InvalidCustomerDataException.class)
//    public ResponseEntity<ErrorResponse> handleInvalidCustomerDataException(InvalidCustomerDataException ex) {
//
//    }

    @org.springframework.web.bind.annotation.ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationResponse> handleValidationException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            String fieldName = error.getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        }
        ValidationResponse response = new ValidationResponse();
        response.setErrors(errors);
        response.setLocalDateTime(LocalDateTime.now());
        response.setStatus(HttpStatus.BAD_REQUEST.value());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
