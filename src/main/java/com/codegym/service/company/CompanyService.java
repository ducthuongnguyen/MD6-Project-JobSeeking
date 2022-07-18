package com.codegym.service.company;

import com.codegym.model.entity.Company;
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
        return null;
    }

    @Override
    public Optional<Company> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Company save(Company company) {
        return null;
    }

    @Override
    public void remove(Long id) {

    }
}
