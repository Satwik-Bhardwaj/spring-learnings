package com.learnings.jwtdemo.service;

import com.learnings.jwtdemo.model.User;

import java.util.List;

public interface UserService {
    String saveUser(User user);

    List<User> getAllUsers();
}
