package com.LeaveApplication.UserServices.Exception;

public class Error {

    private Integer errorcode;
    private String message;

    Error(Integer errorcode,String message){
        this.errorcode = errorcode;
        this.message = message;
    }
}
