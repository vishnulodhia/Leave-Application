//package com.LeaveApplication.UserServices;
//
//
//import com.LeaveApplication.UserServices.Exception.DuplicateUserRegistrationException;
//import com.LeaveApplication.UserServices.Exception.Error;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.ErrorResponse;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@ControllerAdvice
//public class GlobalException {
//
//    @ExceptionHandler(DuplicateUserRegistrationException.class)
//    public ResponseEntity<?> handleUserNotFoundException(DuplicateUserRegistrationException ex) {
//        Error e = new Error();
//        return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
//    }
//
//}
