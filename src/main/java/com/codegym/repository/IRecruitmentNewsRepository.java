package com.codegym.repository;

import com.codegym.model.entity.RecruitmentNews;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRecruitmentNewsRepository extends JpaRepository<RecruitmentNews,Long> {
}
