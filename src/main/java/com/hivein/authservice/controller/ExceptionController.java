package com.hivein.authservice.controller;

import com.hivein.authservice.exception.BaseException;
import com.hivein.authservice.model.response.ErrorResponse;
import com.hivein.authservice.util.StatusCodes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.net.ConnectException;

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
        return new ResponseEntity<>(new ErrorResponse(StatusCodes.BAD_REQUEST.getCode(), ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    protected ResponseEntity<ErrorResponse> handleConc(ConstraintViolationException ex, WebRequest request) {
        log.error("Error ConstraintViolation");
        return new ResponseEntity<>(new ErrorResponse(
                StatusCodes.BAD_REQUEST.getCode(),
                "Violated Constraints"),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    protected ResponseEntity<ErrorResponse> handleConc(IllegalArgumentException ex, WebRequest request) {
        log.error("Error ConstraintViolation");
        return new ResponseEntity<>(new ErrorResponse(
                StatusCodes.BAD_REQUEST.getCode(),
                "Illegal argument exception"),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConnectException.class)
    protected ResponseEntity<ErrorResponse> handleConc(ConnectException ex, WebRequest request) {
        log.error("Error ConstraintViolation");
        return new ResponseEntity<>(new ErrorResponse(
                StatusCodes.REQUEST_TIMEOUT.getCode(),
                "Connection Exception"),
                HttpStatus.REQUEST_TIMEOUT);
    }

    @ExceptionHandler(DisabledException.class)
    protected ResponseEntity<ErrorResponse> handleConc(DisabledException ex, WebRequest request) {
        log.error("Error DisabledException");
        return new ResponseEntity<>(new ErrorResponse(
                StatusCodes.FORBIDDEN.getCode(),
                "ACCOUNT IS NOT ACTIVATED"),
                HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(InternalAuthenticationServiceException.class)
    protected ResponseEntity<ErrorResponse> handleConc(InternalAuthenticationServiceException ex, WebRequest request) {
        log.error("Error USER unknown");
        return new ResponseEntity<>(new ErrorResponse(
                StatusCodes.UNAUTHORIZED.getCode(),
                "USER UNKNOWN"),
                HttpStatus.UNAUTHORIZED);
    }


}
