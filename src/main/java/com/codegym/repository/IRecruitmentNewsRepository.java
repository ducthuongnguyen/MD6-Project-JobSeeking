package com.codegym.repository;

import com.codegym.model.entity.Company;
import com.codegym.model.entity.Field;
import com.codegym.model.entity.RecruitmentNews;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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

    //tim theo ten,nganh,dia diem,luong nho nhat
    @Query (value ="select * from recruitment_news r inner join fields f on f.id=r.field_id where r.title like :title  or r.working_place like :title or r.salary_from like :title or f.name like :title", nativeQuery = true)
    Iterable<RecruitmentNews> findAllRecruitmentNews(@Param("title") String title);

    //tim theo dia diem
    @Query (value ="select * from recruitment_news r where  r.working_place like :title ", nativeQuery = true)
    Iterable<RecruitmentNews> findAllByWorkingPlace(@Param("title") String title);

    //tim kiem nhanh theo ten chuyen nganh
    @Query (value ="select * from recruitment_news r inner join fields f on f.id=r.field_id where f.name like :title", nativeQuery = true)
    Iterable<RecruitmentNews> findRecruimentByFieldName(@Param("title") String title);

    //tim kiem nhanh theo tieu de,ten cong ty
    @Query (value ="select * from recruitment_news r inner join companies c on c.id=r.company_id where r.title like :title or c.name like :title", nativeQuery = true)
    Iterable<RecruitmentNews> findRecruimentByTitleAndCompanyName(@Param("title") String title);

    //tim theo ten nganh,dia diem
    @Query (value ="select * from recruitment_news r inner join fields f on f.id=r.field_id where r.working_place like :title or f.name like :title", nativeQuery = true)
    Iterable<RecruitmentNews> findRecruimentByFieldAndWorkingPlace(@Param("title") String title);

    //tim theo ten tieu de ,kinh nghiem,thanh pho
    @Query (value ="select * from recruitment_news r where  r.working_place like :title or r.required_experience like :title or r.title like :title", nativeQuery = true)
    Iterable<RecruitmentNews> findRecruitmentNewsByTitleWorkingPlaceExperience(@Param("title") String title);
}
