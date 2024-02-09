package com.learnings.jwtdemo.service;

import com.learnings.jwtdemo.model.User;
import com.learnings.jwtdemo.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder pwdEncoder;

    // save method
    @Override
    @Transactional
    public String saveUser(User user) {
        // encrypting the password
        user.setPassword(pwdEncoder.encode(user.getPassword()));

        return userRepository.save(user).getUsername();
    }

    // get user by username
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    // --load user by username----------------------------------
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> op = findByUsername(username);
        if(op.isEmpty())
            throw new UsernameNotFoundException("User not exist");

        // read user (from DB)
        User user = op.get();

        return new org.springframework.security.core.userdetails.User(
                username,
                user.getPassword(),
                user.getRoles().stream()
                        .map(role->new SimpleGrantedAuthority(role))
                        .collect(Collectors.toList())
        );

    }


    // get all user
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
