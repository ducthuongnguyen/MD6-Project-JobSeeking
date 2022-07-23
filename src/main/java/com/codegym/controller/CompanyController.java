package com.codegym.controller;

import com.codegym.constant.Constant;
import com.codegym.model.entity.Company;
import com.codegym.service.company.ICompanyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.Optional;

import static com.codegym.constant.Constant.Status.LOCK;
import static com.codegym.constant.Constant.Status.UNLOCK;

@RestController
@RequestMapping("/companies")
@CrossOrigin("*")
@Slf4j
public class CompanyController {
    @Autowired
    private ICompanyService companyService;

    @GetMapping("/{id}")
    public ResponseEntity<Company> findById(@PathVariable Long id) {
        Optional<Company> company = companyService.findById(id);
        if (!company.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(company.get(), HttpStatus.OK);
    }

    @PutMapping(value = "/{id}",  consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Company> update(@PathVariable Long id, @RequestPart(value = "company") Company company,@RequestPart(value = "image", required = false) MultipartFile file) {
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
        companyService.save(company);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/approve/{id}")
    public ResponseEntity<Company> approveCompany(@PathVariable Long id) {
        Optional<Company> companyOptional = companyService.findById(id);
        if (!companyOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        companyOptional.get().setApproval(Constant.Approval.YES);
        companyOptional.get().setStatus(UNLOCK);
        companyService.save(companyOptional.get());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Page<Company>> findAll(Pageable pageable) {
        return new ResponseEntity<>(companyService.findAll(pageable), HttpStatus.OK);
    }

//    @GetMapping()
//    public ResponseEntity<Iterable<Company>> findAll() {
//        return new ResponseEntity<>(companyService.findAll(), HttpStatus.OK);
//    }

    @PutMapping("/update-status/{id}")
    public ResponseEntity<Company> updateStatus(@PathVariable Long id) {
        Optional<Company> companyOptional = companyService.findById(id);
        if (!companyOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            if (companyOptional.get().getStatus().equals(LOCK)) {
                companyOptional.get().setStatus(UNLOCK);
            } else companyOptional.get().setStatus(LOCK);
        }
        return new ResponseEntity<>(companyService.save(companyOptional.get()), HttpStatus.OK);
    }

    @PutMapping("/set-propose/{id}")
    public ResponseEntity<Company> setProposal(@PathVariable Long id) {
        Optional<Company> company = companyService.findById(id);
        if (!company.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            if (company.get().getProposed().equals(Constant.Proposal.NO)) {
                company.get().setProposed(Constant.Proposal.YES);
            } else company.get().setProposed(Constant.Proposal.NO);
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

    //danh sach cong ty bi khoa
    @GetMapping("/locked-company")
    public ResponseEntity<Iterable<Company>> findAllLockCompanies() {
        return new ResponseEntity<>(companyService.findAllLockCompanies(), HttpStatus.OK);
    }
}
