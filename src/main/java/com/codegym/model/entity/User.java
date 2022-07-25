package com.codegym.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String username;
    private String email;
    private String password;
    private String phoneNumber;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns =
    @JoinColumn(name = "user_id"), inverseJoinColumns =
    @JoinColumn(name = "role_id"))
    Set<Role> roles = new HashSet<>();

    public User(String name, String username, String email, String encode) {
        this.name=name;
        this.username=username;
        this.email=email;
        this.password=encode;
    }
    public User(String name, String username, String email,String phoneNumber, String encode) {
        this.name=name;
        this.username=username;
        this.email=email;
        this.phoneNumber=phoneNumber;
        this.password=encode;
    }
}
