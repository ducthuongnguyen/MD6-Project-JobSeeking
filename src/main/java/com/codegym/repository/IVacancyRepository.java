package com.codegym.repository;

import com.codegym.model.entity.Company;
import com.codegym.model.entity.Vacancy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IVacancyRepository extends JpaRepository<Vacancy, Long> {
}
