package com.codegym.model.entity;

import com.codegym.constant.Constant;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "companies")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Size(min = 3, max = 50)
    private String name;
    @NotBlank
    @Email
    private String email;
    @JsonIgnore
    @Size(min = 6)
    private String password;
    @Lob
    private String avatar;
    @Lob
    @NotBlank
    private String address;
    @NotBlank
    @Pattern(regexp = "^\\+84\\d{9,10}$")
    private String phoneNumber;
    @NotBlank
    @Lob
    private String introduction;
    private Constant.Status status;
    private Constant.Proposal proposed;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "company_role", joinColumns =
    @JoinColumn(name = "company_id"), inverseJoinColumns =
    @JoinColumn(name = "role_id"))
    Set<Role> roles = new HashSet<>();

    public Company(String name, String email, String encode, String avatar, String address, String phoneNumber, String introduction, Constant.Status status, Constant.Proposal proposed) {
        this.name = name;
        this.email = email;
        this.password = encode;
        this.avatar = avatar;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.introduction = introduction;
        this.status = status;
        this.proposed = proposed;

    }

}
