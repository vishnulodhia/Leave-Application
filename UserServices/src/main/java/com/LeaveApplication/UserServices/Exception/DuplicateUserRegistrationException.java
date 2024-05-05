package com.LeaveApplication.UserServices.Exception;


import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;


public class DuplicateUserRegistrationException extends RuntimeException{

//    public ResponseEntity<?> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
//        // Basic handling, you can customize this part to provide more detailed feedback
//        String errorMessage = "A record with the same username or email already exists.";
//        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
//    }

    public DuplicateUserRegistrationException(String msg){
        super(msg);
    }

}
