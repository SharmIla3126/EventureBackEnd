package com.ford.demo.controller;

import com.ford.demo.model.Signup;
import com.ford.demo.service.SignupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sign-up")
@CrossOrigin
public class SignupController {

    @Autowired
    private SignupService signupService;

    @PostMapping
    public ResponseEntity<Signup> registerUser(@RequestBody Signup signup) {
        Signup createdUser = signupService.registerUser(signup);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

}
