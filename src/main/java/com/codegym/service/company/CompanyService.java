package com.codegym.service.company;

import com.codegym.model.dto.response.CompanyResponse;
import com.codegym.model.entity.Company;
import com.codegym.model.entity.User;
import com.codegym.repository.ICompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CompanyService implements ICompanyService {
    @Autowired
    private ICompanyRepository companyRepository;

    @Override
    public Iterable<Company> findAll() {
        return companyRepository.findAll();
    }

    @Override
    public Optional<Company> findById(Long id) {
        return companyRepository.findById(id);
    }

    @Override
    public Company save(Company company) {
        return companyRepository.save(company);
    }

    @Override
    public void remove(Long id) {
companyRepository.deleteById(id);
    }

    public Optional<Company> findByName(String name) {
        return companyRepository.findByName(name);
    }

    public Boolean existsByName(String name) {
        return companyRepository.existsByName(name);
    }

    public Boolean existsByEmail(String email) {
        return companyRepository.existsByEmail(email);
    }
}
