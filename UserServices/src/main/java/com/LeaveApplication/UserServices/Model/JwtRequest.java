package com.LeaveApplication.UserServices.Model;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class JwtRequest {

    private  String username;
    private  String password;
}
