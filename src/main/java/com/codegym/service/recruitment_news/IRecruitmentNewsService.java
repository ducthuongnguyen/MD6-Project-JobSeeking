package com.codegym.service.recruitment_news;

import com.codegym.model.entity.RecruitmentNews;
import com.codegym.service.IGeneralService;

public interface IRecruitmentNewsService extends IGeneralService<RecruitmentNews> {
    Iterable<RecruitmentNews> findAllProposedRecruitmentNews();
}
