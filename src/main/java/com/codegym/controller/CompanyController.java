package com.codegym.controller;
import com.codegym.constant.Constant;
import com.codegym.model.entity.Company;
import com.codegym.service.company.ICompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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
        return new ResponseEntity<>( HttpStatus.OK);
    }

    @PutMapping("change-status/{id}")
    public ResponseEntity<Company> updateStatus(@PathVariable Long id) {
        Optional<Company> companyOptional = companyService.findById(id);
        if (!companyOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Company company = new Company();

        company.setId(companyOptional.get().getId());
        company.setName(companyOptional.get().getName());
        company.setEmail(companyOptional.get().getEmail());
        company.setPassword(companyOptional.get().getPassword());
        company.setAvatar(companyOptional.get().getAvatar());
        company.setAddress(companyOptional.get().getAddress());
        company.setPhoneNumber(companyOptional.get().getPhoneNumber());
        company.setIntroduction(companyOptional.get().getIntroduction());
        company.setProposed(companyOptional.get().getProposed());
        company.setRoles(companyOptional.get().getRoles());
        if (companyOptional.get().getStatus()== Constant.Status.LOCK){
            company.setStatus(Constant.Status.UNLOCK);
        } else {
            company.setStatus(Constant.Status.LOCK);
        }
        companyService.save(company);
        return new ResponseEntity<>( HttpStatus.OK);
    }

}
