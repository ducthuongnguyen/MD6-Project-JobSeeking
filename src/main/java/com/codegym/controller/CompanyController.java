package com.codegym.controller;

import com.codegym.constant.Constant;
import com.codegym.model.entity.Company;
import com.codegym.model.entity.Role;
import com.codegym.model.entity.User;
import com.codegym.service.company.ICompanyService;
import com.codegym.service.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashSet;
import java.io.IOException;
import java.util.Base64;
import java.util.Optional;
import java.util.Set;

import static com.codegym.constant.Constant.Status.Khóa;
import static com.codegym.constant.Constant.Status.Mở;

@RestController
@RequestMapping("/companies")
@CrossOrigin("*")
@Slf4j
public class CompanyController {
    @Autowired
    private ICompanyService companyService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<Company> findById(@PathVariable Long id) {
        Optional<Company> company = companyService.findById(id);
        if (!company.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(company.get(), HttpStatus.OK);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> update(@PathVariable Long id, @RequestPart(value = "company") Company company, @RequestPart(value = "image", required = false) MultipartFile file) {
        Optional<Company> companyOptional = companyService.findById(id);
        if (!companyOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        String image = null;
        try {
            byte[] fileContent = file.getBytes();
            String outputFile = Base64.getEncoder().encodeToString(fileContent);
            String contentType = file.getContentType();
            image = "data:".concat(contentType).concat(";base64,").concat(outputFile);

        } catch (IOException e) {
            log.info("Error in file get bytes ``", file);
        }
        company.setAvatar(image);
        company.setId(companyOptional.get().getId());
        company.setEmail(companyOptional.get().getEmail());
        company.setPassword(companyOptional.get().getPassword());
        company.setStatus(companyOptional.get().getStatus());
        company.setProposed(companyOptional.get().getProposed());
        company.setRoles(companyOptional.get().getRoles());
        company.setApproval(companyOptional.get().getApproval());
        Company newCompany = companyService.save(company);
        return new ResponseEntity<>(newCompany, HttpStatus.OK);
    }

    @PutMapping("/approve/{id}")
    public ResponseEntity<Company> approveCompany(@PathVariable Long id) {
        Optional<Company> companyOptional = companyService.findById(id);
        if (!companyOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        User user = new User(companyOptional.get().getName(), companyOptional.get().getEmail(), companyOptional.get().getEmail(), companyOptional.get().getPhoneNumber(), companyOptional.get().getPassword());
        companyOptional.get().setApproval(Constant.Approval.YES);
        companyOptional.get().setStatus(Mở);
        Company companySave = companyOptional.get();
        companyService.save(companySave);
        Set<Role> rolesCompany = companySave.getRoles();
        Set<Role> roles = new HashSet<>();
        roles.addAll(rolesCompany);
        user.setRoles(roles);
        userService.save(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Page<Company>> findAll(Pageable pageable) {
        return new ResponseEntity<>(companyService.findAll(pageable), HttpStatus.OK);
    }

    @PutMapping("/update-status/{id}")
    public ResponseEntity<Company> updateStatus(@PathVariable Long id) {
        Optional<Company> companyOptional = companyService.findById(id);
        if (!companyOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            if (companyOptional.get().getStatus().equals(Khóa)) {
                companyOptional.get().setStatus(Mở);
            } else companyOptional.get().setStatus(Khóa);
        }
        return new ResponseEntity<>(companyService.save(companyOptional.get()), HttpStatus.OK);
    }

    @PutMapping("/set-propose/{id}")
    public ResponseEntity<Company> setProposal(@PathVariable Long id) {
        Optional<Company> company = companyService.findById(id);
        if (!company.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            if (company.get().getProposed().equals(Constant.Proposal.Không)) {
                company.get().setProposed(Constant.Proposal.Có);
            } else company.get().setProposed(Constant.Proposal.Không);
        }
        return new ResponseEntity<>(companyService.save(company.get()), HttpStatus.OK);
    }

    @GetMapping("/proposal-company")
    public ResponseEntity<Iterable<Company>> findAllProposedRecruitmentNews() {
        return new ResponseEntity<>(companyService.findAllProposedCompanies(), HttpStatus.OK);
    }

    //danh sach cong ty cho duyet
    @GetMapping("/pending-company")
    public ResponseEntity<Iterable<Company>> findAllPendingCompanies() {
        return new ResponseEntity<>(companyService.findAllPendingCompanies(), HttpStatus.OK);
    }

    //danh sach cong ty da duyet
    @GetMapping("/approved-company")
    public ResponseEntity<Iterable<Company>> findAllApprovedCompanies() {
        return new ResponseEntity<>(companyService.findAllApprovedCompanies(), HttpStatus.OK);
    }

    //danh sach cong ty khong khoa
    @GetMapping("/unlock-company")
    public ResponseEntity<Iterable<Company>> findAllUnlockCompanies() {
        return new ResponseEntity<>(companyService.findAllUnlockCompanies(), HttpStatus.OK);
    }

    //danh sach cong ty khong khoa phan trang
    @GetMapping("/unlock-company-page")
    public ResponseEntity<Page<Company>> findAllUnlockCompanies(Pageable pageable) {
        return new ResponseEntity<>(companyService.findAllUnlockCompanies(pageable), HttpStatus.OK);
    }

    //danh sach cong ty bi khoa
    @GetMapping("/locked-company")
    public ResponseEntity<Iterable<Company>> findAllLockCompanies() {
        return new ResponseEntity<>(companyService.findAllLockCompanies(), HttpStatus.OK);
    }

}
