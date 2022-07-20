package com.codegym.controller;


import com.codegym.constant.Constant;
import com.codegym.model.dto.request.SignInForm;
import com.codegym.model.dto.request.SignUpCompanyForm;
import com.codegym.model.dto.request.SignUpForm;
import com.codegym.model.dto.response.JwtResponse;
import com.codegym.model.dto.response.ResponMessage;
import com.codegym.model.entity.Company;
import com.codegym.model.entity.Role;
import com.codegym.model.entity.User;
import com.codegym.security.jwt.JwtProvider;
import com.codegym.security.jwt.JwtTokenFilter;
import com.codegym.security.userprincal.UserPrinciple;
import com.codegym.service.company.CompanyService;
import com.codegym.service.role.RoleServiceImpl;
import com.codegym.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@CrossOrigin("*")
@RequestMapping("")
@RestController
public class AuthController {
    @Autowired
    UserService userService;
    @Autowired
    CompanyService companyService;
    @Autowired
    RoleServiceImpl roleService;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtProvider jwtProvider;
    @Autowired
    JwtTokenFilter jwtTokenFilter;

    @PostMapping("/sign-up")
    public ResponseEntity<?> register(@Valid @RequestBody SignUpForm signUpForm) {
        if (userService.existsByUsername(signUpForm.getUsername())) {
            return new ResponseEntity<>(new ResponMessage("no_user"), HttpStatus.OK);
        }
        if (userService.existsByEmail(signUpForm.getEmail())) {
            return new ResponseEntity<>(new ResponMessage("no_email"), HttpStatus.OK);
        }

        if (signUpForm.getAvatar() == null || signUpForm.getAvatar().trim().isEmpty()) {
            signUpForm.setAvatar("https://firebasestorage.googleapis.com/v0/b/blog-eab4c.appspot.com/o/images%2Fth%20(1).jpg?alt=media&token=aff3ee5b-f7c2-419a-98bb-9dd3e48041bd");
        }
        User user = new User(signUpForm.getName(), signUpForm.getUsername(), signUpForm.getEmail(), passwordEncoder.encode(signUpForm.getPassword()));
        Set<String> strRoles = signUpForm.getRoles();
        Set<Role> roles = new HashSet<>();
        strRoles.forEach(role -> {
            switch (role) {
                case "admin":
                    Role adminRole = roleService.findByName(Constant.RoleName.ADMIN).orElseThrow(
                            () -> new RuntimeException("Role not found")
                    );
                    roles.add(adminRole);
                    break;
                case "company":
                    Role companyRole = roleService.findByName(Constant.RoleName.COMPANY).orElseThrow(
                            () -> new RuntimeException("Role not found")
                    );
                    roles.add(companyRole);
                    break;
                default:
                    Role userRole = roleService.findByName(Constant.RoleName.USER).orElseThrow(
                            () -> new RuntimeException("Role not found"));
                    roles.add(userRole);

            }
        });
        user.setRoles(roles);
        userService.save(user);
        return new ResponseEntity<>(new ResponMessage("Create User Account Success!"), HttpStatus.OK);
    }

    @PostMapping("/sign-in")
    public ResponseEntity<?> login(@Valid @RequestBody SignInForm signInForm) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signInForm.getUsername(), signInForm.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtProvider.createToken(authentication);
        UserPrinciple userPrinciple = (UserPrinciple) authentication.getPrincipal();
        return ResponseEntity.ok(new JwtResponse(token, userPrinciple.getId(), userPrinciple.getName(), userPrinciple.getUsername(), userPrinciple.getEmail(), userPrinciple.getAvatar(), userPrinciple.getAuthorities()));
    }

    @PostMapping("/sign-in-company")
    public ResponseEntity<?> login1(@Valid @RequestBody SignUpCompanyForm signUpCompanyForm) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signUpCompanyForm.getEmail(), signUpCompanyForm.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtProvider.createToken(authentication);
        UserPrinciple userPrinciple = (UserPrinciple) authentication.getPrincipal();
        return ResponseEntity.ok(new JwtResponse(token, userPrinciple.getId(), userPrinciple.getName(), userPrinciple.getEmail(), userPrinciple.getAvatar(), userPrinciple.getAuthorities()));
    }

    @PostMapping("/sign-up-company")
    public ResponseEntity<?> register(@Valid @RequestBody SignUpCompanyForm signUpCompanyForm, SignUpForm signUpForm) {
        if (companyService.existsByName(signUpCompanyForm.getName())) {
            return new ResponseEntity<>(new ResponMessage("company name is existed"), HttpStatus.OK);
        }
        if (companyService.existsByEmail(signUpCompanyForm.getEmail())) {
            return new ResponseEntity<>(new ResponMessage("company email is existed"), HttpStatus.OK);
        }
        if (signUpForm.getAvatar() == null || signUpForm.getAvatar().trim().isEmpty()) {
            signUpForm.setAvatar("https://firebasestorage.googleapis.com/v0/b/blog-eab4c.appspot.com/o/images%2Fth%20(1).jpg?alt=media&token=aff3ee5b-f7c2-419a-98bb-9dd3e48041bd");
        }
        if (signUpCompanyForm.getAvatar() == null || signUpCompanyForm.getAvatar().trim().isEmpty()) {
            signUpCompanyForm.setAvatar("https://firebasestorage.googleapis.com/v0/b/blog-eab4c.appspot.com/o/images%2Fth%20(1).jpg?alt=media&token=aff3ee5b-f7c2-419a-98bb-9dd3e48041bd");
        }
        Company company = new Company(signUpCompanyForm.getName(), signUpCompanyForm.getEmail(), passwordEncoder.encode(signUpCompanyForm.getPassword()), signUpCompanyForm.getAvatar(), signUpCompanyForm.getAddress(),
                signUpCompanyForm.getPhoneNumber(), signUpCompanyForm.getIntroduction(), signUpCompanyForm.getStatus(), signUpCompanyForm.getProposed());
        User user = new User(signUpCompanyForm.getName(), signUpCompanyForm.getEmail(), signUpCompanyForm.getEmail(), signUpCompanyForm.getPhoneNumber(), passwordEncoder.encode(signUpCompanyForm.getPassword()));
        Set<String> strRoles = signUpCompanyForm.getRoles();
        Set<Role> roles = new HashSet<>();
        Role adminRole = roleService.findByName(Constant.RoleName.COMPANY).orElseThrow(
                () -> new RuntimeException("Role not found")
        );
        roles.add(adminRole);
        company.setRoles(roles);
        company.setStatus(Constant.Status.UNLOCK);
        company.setProposed(Constant.Proposal.NO);
        user.setRoles(roles);
        companyService.save(company);
        userService.save(user);

        return new ResponseEntity<>(new ResponMessage("Create company Account Success!"), HttpStatus.OK);
    }
}
