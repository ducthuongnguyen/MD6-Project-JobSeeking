package com.codegym.service.recruitment_news;

import com.codegym.model.entity.Company;
import com.codegym.model.entity.RecruitmentNews;
import com.codegym.service.IGeneralService;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IRecruitmentNewsService extends IGeneralService<RecruitmentNews> {
    Iterable<RecruitmentNews> findAllProposedRecruitmentNews();

    Iterable<RecruitmentNews> findAllByCompany(Company company);
//    Page<RecruitmentNews> findAll(Pageable pageable);

    //danh sach tin tuyen dung bi khoa
    Iterable<RecruitmentNews> findAllLockedRecruitmentNews();

    // danh sach tuyen dung khong khoa
    Iterable<RecruitmentNews> findAllUnlockRecruitmentNews();

//tim kiem nhanh theo ten linh vuc noi lam viec luong nho nhat
//Iterable<RecruitmentNews> findAllByTitleContainingAndWorkingPlaceContaining(String title,String workingplace);

    Iterable<RecruitmentNews> findAllByTitleContaining(String title);



    //tim kiem nhanh theo ten linh vuc noi lam viec luong nho nhat
//Iterable<RecruitmentNews> findAllByTitleContainingAndWorkingPlaceContaining(String title,String workingplace);
//    Iterable<RecruitmentNews> findAllByTitleContaining(String title);
    Iterable<RecruitmentNews> findAllRecruitmentNews(@Param("title") String title);


}
