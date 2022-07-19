package com.codegym.model.dto.request;

import com.codegym.constant.Constant;

import java.util.Set;

public class SignUpCompanyForm {
    private Long id;
    private String name;
    private String email;
    private String password;
    private String avatar;
    private String address;
    private String phoneNumber;
    private String introduction;
    private Constant.Status status;
    private Integer proposed;
    private Set<String> roles;

    public SignUpCompanyForm() {
    }

    public SignUpCompanyForm(Long id, String name, String email, String password, String avatar, String address, String phoneNumber, String introduction, Constant.Status status, Integer proposed, Set<String> roles) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.avatar = avatar;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.introduction = introduction;
        this.status = status;
        this.proposed = proposed;
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public Constant.Status getStatus() {
        return status;
    }

    public void setStatus(Constant.Status status) {
        this.status = status;
    }

    public Integer getProposed() {
        return proposed;
    }

    public void setProposed(Integer proposed) {
        this.proposed = proposed;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

}
