package com.ransankul.roleBaseAuth.controller;



import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.ransankul.roleBaseAuth.exception.ResourceNotFoundException;

@ControllerAdvice
public class UserControllerAdvice {
	
	// This class handle all the exception and return a well structured message to user
	
	
	//If given ID user not found then this Exception is throw. this is the custom Exception
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleException(ResourceNotFoundException ex) {
    	Long id = ex.getId();
        String errorMessage = ex.getMessage();
        String message = errorMessage+" with this id "+id;
        return new ResponseEntity<>(message,HttpStatus.BAD_REQUEST);
    }

    //If requested user are not alowed to use the requested API
    @ExceptionHandler(value = {AccessDeniedException.class})
    public ResponseEntity<String> handleAccessDeniedException(AccessDeniedException ex) {
        String errorMessage = "You are not authorized to perform this action.";
        return new ResponseEntity<String>(errorMessage,HttpStatus.FORBIDDEN);
    }

    //This is handle all the remaining Exception. 
    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<String> handleGeneralException(Exception ex, WebRequest request) {
        String errorMessage = "An error occurred while processing your request.";
        return new ResponseEntity<String>(errorMessage,HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

