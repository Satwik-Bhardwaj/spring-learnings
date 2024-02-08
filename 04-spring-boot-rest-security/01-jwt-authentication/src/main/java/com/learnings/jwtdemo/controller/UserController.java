package com.learnings.jwtdemo.controller;

import com.learnings.jwtdemo.model.User;
import com.learnings.jwtdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/user")
public class UserController {

    @Autowired
    private UserService userService;

    // save user in database
    @PostMapping("/save")
    public ResponseEntity<String> saveUser(@RequestBody User user) {
        String username = userService.saveUser(user) + " - saved.";
        return new ResponseEntity<String>(username, HttpStatus.OK);
    }

    @GetMapping("")
    public List<User> getAllUser() {
        List<User> users = userService.getAllUsers();
        return users;
    }
}
