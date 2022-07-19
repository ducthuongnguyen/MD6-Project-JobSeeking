package com.codegym.service.company;

import com.codegym.model.entity.Company;
import com.codegym.model.entity.User;
import com.codegym.service.IGeneralService;

import java.util.Optional;

public interface ICompanyService extends IGeneralService<Company> {
    //tim kiem co ton tai trong DB khong
    Optional<Company> findByName(String name);

    //kt xem user da co torng DB chua khi tao du lieu
    Boolean existsByName(String name);

    //kt xem email da co torng DB chua khi tao du lieu
    Boolean existsByEmail(String email);
}
