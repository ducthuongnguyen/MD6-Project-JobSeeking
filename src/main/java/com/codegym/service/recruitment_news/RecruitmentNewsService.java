package com.codegym.service.recruitment_news;

import com.codegym.model.entity.RecruitmentNews;
import com.codegym.repository.IRecruitmentNewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
}
