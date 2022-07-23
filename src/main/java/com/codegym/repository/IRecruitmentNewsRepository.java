package com.codegym.repository;

import com.codegym.model.entity.Company;
import com.codegym.model.entity.Field;
import com.codegym.model.entity.RecruitmentNews;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IRecruitmentNewsRepository extends JpaRepository<RecruitmentNews, Long> {
    @Query(value = "select * from recruitment_news where proposed = 0;", nativeQuery = true)

    Iterable<RecruitmentNews> findAllProposedRecruitmentNews();

    //danh sach tin tuyen dung bi khoa
    @Query(value = "select * from recruitment_news where status = 0 ;", nativeQuery = true)
    Iterable<RecruitmentNews> findAllLockedRecruitmentNews();

    // danh sach tuyen dung khong khoa
    @Query(value = "select * from recruitment_news where status = 1;", nativeQuery = true)
    Iterable<RecruitmentNews> findAllUnlockRecruitmentNews();

    Iterable<RecruitmentNews> findAllByCompany(Company company);
//    Page<RecruitmentNews> findAll(Pageable pageable);
    Iterable<RecruitmentNews> findAllByTitleContainingAndWorkingPlaceContaining(String title,String workingplace);
}
