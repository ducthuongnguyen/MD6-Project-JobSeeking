package com.codegym.repository;

import com.codegym.model.entity.RecruitmentNews;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
@Repository
public interface IRecruitmentNewsRepository extends JpaRepository<RecruitmentNews, Long> {
    //danh sach tin tuyen dung duoc de xuat
    @Query(value = "select * from recruitment_news where proposed = 0;", nativeQuery = true)
    Iterable<RecruitmentNews> findAllProposedRecruitmentNews();

    //danh sach tin tuyen dung bi khoa
    @Query(value = "select * from recruitment_news where status = 0 ;", nativeQuery = true)
    Iterable<RecruitmentNews> findAllLockedRecruitmentNews();

    // danh sach tuyen dung khong khoa
    @Query(value = "select * from recruitment_news where status = 1;", nativeQuery = true)
    Iterable<RecruitmentNews> findAllUnlockRecruitmentNews();
}
