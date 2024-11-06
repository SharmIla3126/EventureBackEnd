package com.ford.demo.controller;

import java.io.Console;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ford.demo.model.LoginRequest;
import com.ford.demo.model.LoginResponse;
import com.ford.demo.model.Signup;
import com.ford.demo.repository.ISignupRepository;

@RestController
@RequestMapping("/api")
public class LoginController {

    @Autowired
    private ISignupRepository signupRepository; // Assuming you have a repository

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        String email = loginRequest.getEmail();
        String password = loginRequest.getPassword();

        Optional<Signup> optionalUser = signupRepository.findByEmail(email);

        if (optionalUser.isPresent() && password.equals(optionalUser.get().getPassword())) {
            Signup user = optionalUser.get();
            // Successful login.
            String name = user.getName() != null ? user.getName() : "Guest";
            String phoneNo = user.getPhoneNo() != null ? user.getPhoneNo() : "Guest";
            System.out.println("User name"+ name);
            System.out.println("User no"+ phoneNo);
            LoginResponse response = new LoginResponse(user.getEmail(), user.getType(),name,phoneNo);
            System.out.print(response.getEmail()+""+response.getName()+""+response.getPhoneNo());
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }
}

