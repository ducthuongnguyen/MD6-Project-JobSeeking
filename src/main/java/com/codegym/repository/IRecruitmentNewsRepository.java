package com.codegym.repository;

import com.codegym.constant.Constant;
import com.codegym.model.entity.Company;
import com.codegym.model.entity.RecruitmentNews;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IRecruitmentNewsRepository extends JpaRepository<RecruitmentNews, Long> {
    //danh sach tin de xuat
    @Query(value = "select * from recruitment_news where proposed = 0;", nativeQuery = true)
    Iterable<RecruitmentNews> findProposedRecruitmentNews();

    //danh sach tin theo cong ty
    Iterable<RecruitmentNews> findAllByCompany(Company company);

    //danh sach tin tuyen dung bi khoa
    @Query(value = "select * from recruitment_news where status = 0 ;", nativeQuery = true)
    Iterable<RecruitmentNews> findLockedRecruitmentNews();

    // danh sach tuyen dung khong khoa phan trang
    @Query(value = "select * from recruitment_news where status = 1", nativeQuery = true)
    Page<RecruitmentNews> findUnlockRecruitmentNews(Pageable pageable);

    // danh sach tuyen dung khong khoa
    @Query(value = "select * from recruitment_news where status = 1", nativeQuery = true)
    Iterable<RecruitmentNews> findUnlockRecruitmentNews();

    //tim kiem theo title
    @Query(value = "select * from recruitment_news where status = 1 and title like :title", nativeQuery = true)
    Iterable<RecruitmentNews> findAllByTitleContaining(@Param("title") String title);

    //tim kiem theo chuyen nganh (field)
    @Query(value = "select * from recruitment_news where status = 1 and field like :field", nativeQuery = true)
    Iterable<RecruitmentNews> findAllByFieldContaining(@Param("field") String field);

    //tim theo title, dia diem
    @Query(value = "select * from recruitment_news where status = 1 and " + "(:title is null or title like :title)" +
            " and " + "(:place is null or working_place like :place)", nativeQuery = true)
    Iterable<RecruitmentNews> findAllByTitleContainingAndWorkingPlace(@Param("title") String title, @Param("place") String place);

    //tìm theo job title, salary range, kinh nghiệm, thanh pho, chuyen nganh (field)
    @Query(value = "select * from recruitment_news r  inner join fields f on f.id=r.field_id where r.status = 1 and"
            + " (:title is null or r.title like :title)" + " and"
            + " (:from is null or r.salary_from >= :from)" + " and"
            + " (:to is null or r.salary_to <= :to)" + " and"
            + " (:experience is null or r.required_experience = :experience)" + " and"
            + " (:place is null or r.working_place like :place)" + "and"
            + " (:fieldId is null or f.id = :fieldId)", nativeQuery = true)
    Iterable<RecruitmentNews> findAllByTitleSalaryExperiencePlaceField(@Param("title") String title,
                                                                       @Param("from") Integer from,
                                                                       @Param("to") Integer to,
                                                                       @Param("experience") Integer required_experience,
                                                                       @Param("place") String working_place,
                                                                       @Param("fieldId") Long fieldId);
}
