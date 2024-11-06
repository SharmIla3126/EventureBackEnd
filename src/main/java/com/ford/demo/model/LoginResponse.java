package com.ford.demo.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.ford.demo.model.Signup.UserType;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {
    private String email;
    private UserType userType;
    private String name;
    private String phoneNo;
}
