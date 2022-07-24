package com.codegym.service.recruitment_news;

import com.codegym.model.entity.Company;
import com.codegym.model.entity.RecruitmentNews;
import com.codegym.repository.IRecruitmentNewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecruitmentNewsService implements IRecruitmentNewsService {
    @Autowired
    private IRecruitmentNewsRepository recruitmentNewsRepository;

    @Override
    public Iterable<RecruitmentNews> findAll() {
        return recruitmentNewsRepository.findAll();
    }

    @Override
    public Optional<RecruitmentNews> findById(Long id) {
        return recruitmentNewsRepository.findById(id);
    }

    @Override
    public RecruitmentNews save(RecruitmentNews recruitmentNews) {
        return recruitmentNewsRepository.save(recruitmentNews);
    }

    @Override
    public void remove(Long id) {
        recruitmentNewsRepository.deleteById(id);
    }

    @Override
    public Iterable<RecruitmentNews> findAllProposedRecruitmentNews() {
        return recruitmentNewsRepository.findAllProposedRecruitmentNews();
    }

    @Override
    public Iterable<RecruitmentNews> findAllLockedRecruitmentNews() {
        return recruitmentNewsRepository.findAllLockedRecruitmentNews();
    }

    @Override
    public Iterable<RecruitmentNews> findAllUnlockRecruitmentNews() {
        return recruitmentNewsRepository.findAllUnlockRecruitmentNews();
    }

    @Override
    public Iterable<RecruitmentNews> findAllByCompany(Company company) {
        return recruitmentNewsRepository.findAllByCompany(company);
    }
    @Override
    public Iterable<RecruitmentNews> findAllRecruitmentNews(String title) {
        String tagSearch = "%" + title + "%";
        return recruitmentNewsRepository.findAllRecruitmentNews(tagSearch);
    }

    @Override
    public Iterable<RecruitmentNews> findAllByWorkingPlace(String title) {
        String tagSearch = "%" + title + "%";
        return recruitmentNewsRepository.findAllByWorkingPlace(tagSearch);
    }

    @Override
    public Iterable<RecruitmentNews> findRecruimentByFieldName(String title) {
        String tagSearch = "%" + title + "%";
        return recruitmentNewsRepository.findRecruimentByFieldName(tagSearch);
    }

    @Override
    public Iterable<RecruitmentNews> findRecruimentByTitleAndCompanyName(String title) {
        String tagSearch = "%" + title + "%";
        return recruitmentNewsRepository.findRecruimentByTitleAndCompanyName(tagSearch);
    }

    @Override
    public Iterable<RecruitmentNews> findRecruimentByFieldAndWorkingPlace(String title) {
        String tagSearch = "%" + title + "%";
        return recruitmentNewsRepository.findRecruimentByFieldAndWorkingPlace(tagSearch);
    }

    @Override
    public Iterable<RecruitmentNews> findRecruitmentNewsByTitleWorkingPlaceExperience(String title) {
        String tagSearch = "%" + title + "%";
        return recruitmentNewsRepository.findRecruitmentNewsByTitleWorkingPlaceExperience(tagSearch);
    }


}
