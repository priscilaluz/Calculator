package com.wit.rest.exception;

import com.wit.rest.http.response.DefaultResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<DefaultResponse> handleAllExceptions(Exception ex) {
        return new ResponseEntity<>(new DefaultResponse(ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(BadRequestException.class)
    public final ResponseEntity<DefaultResponse> handleBadRequestExceptions(BadRequestException ex) {
        return new ResponseEntity<>(new DefaultResponse(ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

}
