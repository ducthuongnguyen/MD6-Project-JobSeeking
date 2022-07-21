package com.codegym.controller;

import com.codegym.model.entity.Vacancy;
import com.codegym.service.vacancy.IVacancyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vacancies")
@CrossOrigin("*")
public class VacancyController {
    @Autowired
    private IVacancyService vacancyService;

    
}
