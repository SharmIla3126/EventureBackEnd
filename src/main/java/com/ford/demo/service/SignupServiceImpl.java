package com.ford.demo.service;

import com.ford.demo.model.Signup;
import com.ford.demo.repository.ISignupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class SignupServiceImpl implements SignupService{
    @Autowired
    private ISignupRepository signupRepository;


    @Override
    public Signup registerUser(Signup signup) {
        return signupRepository.save(signup);
    }
}
