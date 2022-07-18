package com.codegym.model.entity;

import com.codegym.constant.Gender;
import com.codegym.constant.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "recruitment_news")
public class RecruitmentNews {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
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
    private Integer employeeQuantity;
    private Integer requiredExperience;
    private Gender gender;
    private String workingPlace;
    private String description;
    private Status status;
    private Integer isProposed;
}
