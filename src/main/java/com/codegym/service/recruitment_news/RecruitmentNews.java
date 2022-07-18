package com.codegym.service.recruitment_news;

import com.codegym.repository.IRecruitmentNewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RecruitmentNews implements IRecruitmentNewsService {
    @Autowired
    private IRecruitmentNewsRepository recruitmentNewsRepository;

    @Override
    public Iterable<RecruitmentNews> findAll() {
        return null;
    }

    @Override
    public Optional<RecruitmentNews> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public RecruitmentNews save(RecruitmentNews recruitmentNews) {
        return null;
    }

    @Override
    public void remove(Long id) {

    }
}
