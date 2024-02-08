package com.learnings.jwtdemo.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Data
@Entity
@Table(name = "User")
public class User{

    @Id
    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @ElementCollection  // check it
    @CollectionTable(   // check it
            name = "role",
            joinColumns = @JoinColumn(name = "username")
    )
    @Column(name = "roles")
    private Set<String> roles;
}