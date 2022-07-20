package com.codegym.repository;

import com.codegym.model.entity.Company;
import java.util.Optional;
import com.codegym.model.entity.RecruitmentNews;
import com.codegym.model.entity.User;
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

    //tim kiem co ton tai trong DB khong
    Optional<Company> findByName(String name);

    //kt xem user da co torng DB chua khi tao du lieu
    Boolean existsByName(String name);

    //kt xem email da co torng DB chua khi tao du lieu
    Boolean existsByEmail(String email);
//    Page<User> findAllByUsernameContaining(String username, Pageable pageable);
}
