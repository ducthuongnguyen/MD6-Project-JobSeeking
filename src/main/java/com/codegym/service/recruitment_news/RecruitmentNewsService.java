package com.codegym.service.recruitment_news;

import com.codegym.repository.IRecruitmentNewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RecruitmentNewsService implements IRecruitmentNewsService {
    @Autowired
    private IRecruitmentNewsRepository recruitmentNewsRepository;

    @Override
    public Iterable<RecruitmentNewsService> findAll() {
        return null;
    }

    @Override
    public Optional<RecruitmentNewsService> findById(Long id) {
        return recruitmentNewsRepository.findById(id);
    }

    @Override
    public RecruitmentNewsService save(RecruitmentNewsService recruitmentNews) {
        return null;
    }

    @Override
    public void remove(Long id) {

    }
}
