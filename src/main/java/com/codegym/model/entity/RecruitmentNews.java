package com.codegym.model.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "recruitment_news")
public class RecruitmentNews {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String newsCode;
    private String title;
    @ManyToOne
    private Company company;
    @ManyToOne
    private Vacancy vacancy;
    @ManyToOne
    private Field field;
    private String salaryFrom;
    private String salaryTo;
    private LocalDate expiredDate;
    private int recruitmentQuantity;
    private String gender;
    private String workingPlace;
    private Status status;

    public RecruitmentNews() {
    }

    public RecruitmentNews(Long id, String newsCode, String title, Company company, Vacancy vacancy, Field field, String salaryFrom, String salaryTo, LocalDate expiredDate, int recruitmentQuantity, String gender, String workingPlace, Status status) {
        this.id = id;
        this.newsCode = newsCode;
        this.title = title;
        this.company = company;
        this.vacancy = vacancy;
        this.field = field;
        this.salaryFrom = salaryFrom;
        this.salaryTo = salaryTo;
        this.expiredDate = expiredDate;
        this.recruitmentQuantity = recruitmentQuantity;
        this.gender = gender;
        this.workingPlace = workingPlace;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNewsCode() {
        return newsCode;
    }

    public void setNewsCode(String newsCode) {
        this.newsCode = newsCode;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Vacancy getVacancies() {
        return vacancy;
    }

    public void setVacancies(Vacancy vacancy) {
        this.vacancy = vacancy;
    }

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }

    public String getSalaryFrom() {
        return salaryFrom;
    }

    public void setSalaryFrom(String salaryFrom) {
        this.salaryFrom = salaryFrom;
    }

    public String getSalaryTo() {
        return salaryTo;
    }

    public void setSalaryTo(String salaryTo) {
        this.salaryTo = salaryTo;
    }

    public LocalDate getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(LocalDate expiredDate) {
        this.expiredDate = expiredDate;
    }

    public int getRecruitmentQuantity() {
        return recruitmentQuantity;
    }

    public void setRecruitmentQuantity(int recruitmentQuantity) {
        this.recruitmentQuantity = recruitmentQuantity;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getWorkingPlace() {
        return workingPlace;
    }

    public void setWorkingPlace(String workingPlace) {
        this.workingPlace = workingPlace;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
