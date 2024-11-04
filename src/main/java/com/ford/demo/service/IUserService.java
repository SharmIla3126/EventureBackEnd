package com.ford.demo.service;

import com.ford.demo.model.Event;
import com.ford.demo.model.User;

import java.util.List;

public interface IUserService {
    public User addUser(User user);
    public List<User> getAllUsers();
    public User getUserById(int id);
    public void deleteUser(int id);
}
