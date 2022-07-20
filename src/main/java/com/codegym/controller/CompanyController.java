package com.codegym.controller;

import com.codegym.constant.Constant;
import com.codegym.model.entity.Company;
import com.codegym.model.entity.RecruitmentNews;
import com.codegym.service.company.ICompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;
import static com.codegym.constant.Constant.Status.LOCK;
import static com.codegym.constant.Constant.Status.UNLOCK;

@RestController
@RequestMapping("/companies")
@CrossOrigin("*")
public class CompanyController {
    @Autowired
    private ICompanyService companyService;

    @GetMapping
    public ResponseEntity<Iterable<Company>> findAll() {
        Iterable<Company> companies = companyService.findAll();
        return new ResponseEntity<>(companies, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Company> findById(@PathVariable Long id) {
        Optional<Company> company = companyService.findById(id);
        if (!company.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(company.get(), HttpStatus.OK);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Company> update(@PathVariable Long id, @RequestBody Company company) {
        Optional<Company> companyOptional = companyService.findById(id);
        if (!companyOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        company.setId(companyOptional.get().getId());
        company.setEmail(companyOptional.get().getEmail());
        company.setPassword(companyOptional.get().getPassword());
        company.setStatus(companyOptional.get().getStatus());
        company.setProposed(companyOptional.get().getProposed());
        company.setRoles(companyOptional.get().getRoles());
        companyService.save(company);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("change-status/{id}")
    public ResponseEntity<Company> updateStatus(@PathVariable Long id) {
        Optional<Company> companyOptional = companyService.findById(id);
        if (!companyOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if (companyOptional.get().getStatus() == Constant.Status.LOCK) {
            companyOptional.get().setStatus(Constant.Status.UNLOCK);
        } else {
            companyOptional.get().setStatus(Constant.Status.LOCK);
        }
        companyService.save(companyOptional.get());
        return new ResponseEntity<>(HttpStatus.OK);
}

//    @GetMapping
//    public ResponseEntity<Page<Company>> findAll(@PageableDefault(value = 1) Pageable pageable) {
//        return new ResponseEntity<>(companyService.findAll(pageable), HttpStatus.OK);
//    }

    @GetMapping()
    public ResponseEntity<Iterable<Company>> findAll() {
        return new ResponseEntity<>(companyService.findAll(), HttpStatus.OK);
    }

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
}
