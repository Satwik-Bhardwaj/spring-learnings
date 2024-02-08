package com.learnings.jwtdemo.service;

import com.learnings.jwtdemo.model.User;
import com.learnings.jwtdemo.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;
    @Override
    @Transactional
    public String saveUser(User user) {
        String username = userRepository.save(user).getUsername();
        return username;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
