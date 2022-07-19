package com.codegym.service.company;

import com.codegym.model.entity.Company;
import com.codegym.service.IGeneralService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface ICompanyService extends IGeneralService<Company> {
    Page<Company> findAll(Pageable pageable);
}
