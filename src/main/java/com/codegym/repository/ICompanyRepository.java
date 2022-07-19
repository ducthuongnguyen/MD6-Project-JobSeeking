package com.codegym.repository;

import com.codegym.model.entity.Company;

import com.codegym.model.entity.RecruitmentNews;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface ICompanyRepository extends JpaRepository<Company, Long> {
    Page<Company> findAll(Pageable pageable);

    @Query(value = "select * from companies where proposed = 0;", nativeQuery = true)
    Iterable<Company> findAllProposedCompanies();
}
