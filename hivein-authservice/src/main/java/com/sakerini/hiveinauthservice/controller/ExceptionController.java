package com.sakerini.hiveinauthservice.controller;

import com.sakerini.hiveinauthservice.exception.BaseException;
import com.sakerini.hiveinauthservice.model.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;

@Slf4j
@ControllerAdvice
public class ExceptionController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BaseException.class)
    protected ResponseEntity<ErrorResponse> handleConc(BaseException ex, WebRequest request) {
        log.error("Error BaseException");
        return new ResponseEntity<>(new ErrorResponse(ex.getCode(), ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BadCredentialsException.class)
    protected ResponseEntity<ErrorResponse> handleConc(BadCredentialsException ex, WebRequest request) {
        log.error("Error Bad Credentials");
        return new ResponseEntity<>(new ErrorResponse("code-400", ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    protected ResponseEntity<ErrorResponse> handleConc(ConstraintViolationException ex, WebRequest request) {
        log.error("Error ConstraintViolation");
        return new ResponseEntity<>(new ErrorResponse(
                "code-400",
                "Violated Constraints"),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    protected ResponseEntity<ErrorResponse> handleConc(IllegalArgumentException ex, WebRequest request) {
        log.error("Error ConstraintViolation");
        return new ResponseEntity<>(new ErrorResponse(
                "code-400",
                "Illegal argument exception"),
                HttpStatus.BAD_REQUEST);
    }


}
