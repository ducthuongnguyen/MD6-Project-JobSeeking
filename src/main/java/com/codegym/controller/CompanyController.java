package com.codegym.controller;

import com.codegym.model.entity.Company;
import com.codegym.model.entity.RecruitmentNews;
import com.codegym.service.company.ICompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
    ICompanyService companyService;

//    @GetMapping
//    public ResponseEntity<Page<Company>> findAll(@PageableDefault(value = 1) Pageable pageable) {
//        return new ResponseEntity<>(companyService.findAll(pageable), HttpStatus.OK);
//    }

    @GetMapping()
    public ResponseEntity<Iterable<Company>> findAll() {
        return new ResponseEntity<>(companyService.findAll(), HttpStatus.OK);
    }

    @PutMapping("/update-status/{id}")
        public ResponseEntity<Company> updateStatus (@PathVariable Long id){
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
}
