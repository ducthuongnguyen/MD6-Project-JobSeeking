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

    //danh sach tin tuyen dung bi khoa
    Iterable<RecruitmentNews> findAllLockedRecruitmentNews();

    // danh sach tuyen dung khong khoa
    Iterable<RecruitmentNews> findAllUnlockRecruitmentNews();
    Page<RecruitmentNews> findPageUnlockRecruitmentNews(Pageable pageable);

    //tim kiem nhanh theo ten linh vuc noi lam viec luong nho nhat
    Iterable<RecruitmentNews> findAllRecruitmentNews(String title);

    //tim kiem nhanh noi lam viec
    Iterable<RecruitmentNews> findAllByWorkingPlace(String title);

    //tim kiem nhanh theo ten chuyen nganh
    Iterable<RecruitmentNews> findRecruimentByFieldName(String title);

    //tim kiem nhanh theo tieu de,ten cong ty
    Iterable<RecruitmentNews> findRecruimentByTitleAndCompanyName(String title);

    //tim kiem nhanh theo ten nganh nghe, noi lam viec
    Iterable<RecruitmentNews> findRecruimentByFieldAndWorkingPlace(String title);

    //tim theo ten tieu de ,kinh nghiem,thanh pho
    Iterable<RecruitmentNews> findRecruitmentNewsByTitleWorkingPlaceExperience(String title);

}
