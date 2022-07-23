package com.codegym.model.dto.request;

import java.util.Set;

public class SignUpForm {
    private String name;
//    private String username;
    private String email;
    private String password;
    private String phoneNumber;
    private Set<String> roles;

    public SignUpForm() {
    }

    public SignUpForm(String name, String email, String password, String phoneNumber, Set<String> roles) {
        this.name = name;

        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.roles = roles;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }
}
