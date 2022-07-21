package com.codegym.repository;

import com.codegym.model.entity.Company;
import com.codegym.model.entity.Field;
import com.codegym.model.entity.RecruitmentNews;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IRecruitmentNewsRepository extends JpaRepository<RecruitmentNews, Long> {
    @Query(value = "select * from recruitment_news where proposed = 0;", nativeQuery = true)

    Iterable<RecruitmentNews> findAllProposedRecruitmentNews();

    Iterable<RecruitmentNews> findAllByCompany(Company company);
//    Page<RecruitmentNews> findAll(Pageable pageable);

}
