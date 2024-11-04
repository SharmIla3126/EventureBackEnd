package com.ford.demo.repository;

import com.ford.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<User,Integer> {
    int countByEventId(int eventId);
}
