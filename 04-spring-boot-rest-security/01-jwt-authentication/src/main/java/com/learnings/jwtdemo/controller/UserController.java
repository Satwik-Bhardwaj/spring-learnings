package com.learnings.jwtdemo.controller;

import com.learnings.jwtdemo.model.User;
import com.learnings.jwtdemo.model.UserRequest;
import com.learnings.jwtdemo.model.UserResponse;
import com.learnings.jwtdemo.service.UserService;
import com.learnings.jwtdemo.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil util;

    @Autowired
    private AuthenticationManager authenticationManager;

    // save user in database
    @PostMapping("/save")
    public ResponseEntity<String> saveUser(@RequestBody User user) {
        String username = userService.saveUser(user) + " - saved.";
        return new ResponseEntity<String>(username, HttpStatus.OK);
    }

    // validate user and generate token(login)
    @PostMapping("/login")
    public ResponseEntity<UserResponse> loginUser(@RequestBody UserRequest userRequest) {
        // validate un/pwd database
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                userRequest.getUsername(), userRequest.getPassword()
        ));

        String token = util.generateToken(userRequest.getUsername());
        return ResponseEntity.ok(new UserResponse(token, "Success! Token Generated."));

    }

    // after login only
    @GetMapping("/welcome")
    public ResponseEntity<String> accessData(Principal principal) {
        return ResponseEntity.ok("Hello User! " + principal.getName());
    }

    // get the list of all the user
    @GetMapping("")
    public List<User> getAllUser() {
        List<User> users = userService.getAllUsers();
        return users;
    }
}
