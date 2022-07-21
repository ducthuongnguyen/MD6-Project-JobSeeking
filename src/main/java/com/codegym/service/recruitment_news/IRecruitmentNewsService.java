package com.codegym.service.recruitment_news;

import com.codegym.model.entity.RecruitmentNews;
import com.codegym.service.IGeneralService;
import org.springframework.data.jpa.repository.Query;

public interface IRecruitmentNewsService extends IGeneralService<RecruitmentNews> {
    Iterable<RecruitmentNews> findAllProposedRecruitmentNews();

    //danh sach tin tuyen dung bi khoa
    Iterable<RecruitmentNews> findAllLockedRecruitmentNews();

    // danh sach tuyen dung khong khoa
    Iterable<RecruitmentNews> findAllUnlockRecruitmentNews();
}
