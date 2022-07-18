package com.codegym.model.entity;

import com.codegym.constant.Status;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Component
@Entity
@Table(name = "companies")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String companyCode;
    private String name;
    private String email;
    private String password;
    private String avatar;
    private String address;
    private String phoneNumber;
    private Status companyStatus;
    private Integer isProposed;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns =
    @JoinColumn(name = "user_id"), inverseJoinColumns =
    @JoinColumn(name = "role_id"))
    Set<Role> roles = new HashSet<>();

    public Company() {
    }

    public Company(Long id, String companyCode, String name, String email, String password, String avatar, String address, String phoneNumber, Status companyStatus, int isPropose, Set<Role> roles) {
        this.id = id;
        this.companyCode = companyCode;
        this.name = name;
        this.email = email;
        this.password = password;
        this.avatar = avatar;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.companyStatus = companyStatus;
        this.isPropose = isPropose;
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
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

    public Status getCompanyStatus() {
        return companyStatus;
    }

    public void setCompanyStatus(Status companyStatus) {
        this.companyStatus = companyStatus;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public int getIsPropose() {
        return isPropose;
    }

    public void setIsPropose(int isPropose) {
        this.isPropose = isPropose;
    }
}
