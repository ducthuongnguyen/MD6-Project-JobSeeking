package com.codegym.service.recruitment_news;

import com.codegym.model.entity.Company;
import com.codegym.model.entity.RecruitmentNews;
import com.codegym.model.entity.User;
import com.codegym.repository.IRecruitmentNewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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
        return recruitmentNewsRepository.findProposedRecruitmentNews();
    }

    @Override
    public Iterable<RecruitmentNews> findAllLockedRecruitmentNews() {
        return recruitmentNewsRepository.findLockedRecruitmentNews();
    }

    @Override
    public Iterable<RecruitmentNews> findProposedRecruitmentNewsAndStatus() {
        return recruitmentNewsRepository.findProposedRecruitmentNewsAndStatus();
    }

    @Override
    public Iterable<RecruitmentNews> findAllUnlockRecruitmentNews() {
        return recruitmentNewsRepository.findUnlockRecruitmentNews();
    }

    @Override
    public Page<RecruitmentNews> findPageUnlockRecruitmentNews(Pageable pageable) {
        return recruitmentNewsRepository.findUnlockRecruitmentNews(pageable);
    }

    @Override
    public Iterable<RecruitmentNews> findAllByFieldContaining(String field) {
        return recruitmentNewsRepository.findAllByFieldContaining(field);
    }

    @Override
    public Iterable<RecruitmentNews> findAllByTitleContainingAndWorkingPlace(String title, String place) {
        return recruitmentNewsRepository.findAllByTitleContainingAndWorkingPlace(title, place);
    }

    @Override
    public Iterable<RecruitmentNews> findAllByTitleContaining(String title) {
        return recruitmentNewsRepository.findAllByTitleContaining(title);
    }

    @Override
    public Iterable<RecruitmentNews> findAllByTitleSalaryExperiencePlaceField(String title, Integer salary_from, Integer salary_to, Integer required_experience, String working_place, Long fieldId) {
        return recruitmentNewsRepository.findAllByTitleSalaryExperiencePlaceField(title, salary_from, salary_to, required_experience, working_place, fieldId);
    }

    @Override
    public Page<RecruitmentNews> findAllByTitleSalaryExperiencePlaceFieldPage(String title, Integer salary_from, Integer salary_to, Integer required_experience, String working_place, Long fieldId, Pageable pageable) {
        return recruitmentNewsRepository.findAllByTitleSalaryExperiencePlaceFieldPage(title, salary_from, salary_to, required_experience, working_place, fieldId, pageable);
    }

    @Override
    public Iterable<RecruitmentNews> findAllByCompany(Company company) {
        return recruitmentNewsRepository.findAllByCompany(company);
    }

    @Override
    public Iterable<RecruitmentNews> findAllByUser(Long id) {
        return recruitmentNewsRepository.findAllByUser(id);
    }


}
