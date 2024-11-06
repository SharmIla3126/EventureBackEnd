package com.ford.demo.repository;

import com.ford.demo.model.Signup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ISignupRepository extends JpaRepository<Signup, Integer> {
    Optional<Signup> findByEmail(String email);
}
