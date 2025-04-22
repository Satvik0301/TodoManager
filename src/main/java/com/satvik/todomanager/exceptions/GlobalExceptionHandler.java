package com.satvik.todomanager.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    //we have to create handler methods for specific exceptions
    Logger logger= LoggerFactory.getLogger(GlobalExceptionHandler.class);


    //Null Pointer Exception
    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<String> nullPointerExceptionHandler(Exception ex){
        logger.error("NullPointer Exception from Global Handler",ex);
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //Resource not Found Exception
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ExceptionResponse> resourceNotFoundHandler(ResourceNotFoundException ex){
        logger.error("Error : {}",ex.getMessage());
        ExceptionResponse exceptionResponse = new ExceptionResponse();
        exceptionResponse.setMessage(ex.getMessage());
        exceptionResponse.setStatus(HttpStatus.NOT_FOUND);
        exceptionResponse.setSuccess(Boolean.FALSE);
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

}
