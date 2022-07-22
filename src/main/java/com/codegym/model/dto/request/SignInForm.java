package com.codegym.model.dto.request;

public class SignInForm {
//    private String username;
    private String email;
    private String password;

    public SignInForm() {
    }

    public SignInForm(String email, String password) {
        this.email = email;
        this.password = password;
    }
//    public SignInForm(String username, String password) {
//        this.username = username;
//        this.password = password;
//    }

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
}
