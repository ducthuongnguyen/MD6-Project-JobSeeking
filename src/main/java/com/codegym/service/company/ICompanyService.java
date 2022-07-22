package com.codegym.service.company;

import com.codegym.model.entity.Company;
import com.codegym.model.entity.RecruitmentNews;
import com.codegym.model.entity.User;
import com.codegym.service.IGeneralService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;


import java.util.Optional;

public interface ICompanyService extends IGeneralService<Company> {
    Page<Company> findAll(Pageable pageable);

    Iterable<Company> findAllProposedCompanies();

    //tim kiem co ton tai trong DB khong
    Optional<Company> findByName(String name);

    //kt xem user da co torng DB chua khi tao du lieu
    Boolean existsByName(String name);

    //kt xem email da co torng DB chua khi tao du lieu
    Boolean existsByEmail(String email);

    Optional<Company> findByEmail(String email);

    //danh sach cong ty cho duyet
    Iterable<Company> findAllPendingCompanies();


    //danh sach cong ty da duyet
    Iterable<Company> findAllApprovedCompanies();

    //danh sach cong ty khong khoa
    Iterable<Company> findAllUnlockCompanies();

    //danh sach cong ty bi khoa
    Iterable<Company> findAllLockCompanies();
}
