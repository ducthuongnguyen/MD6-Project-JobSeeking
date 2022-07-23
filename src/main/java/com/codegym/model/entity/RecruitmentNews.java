package com.codegym.model.entity;

import com.codegym.constant.Constant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
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
    private Integer salaryFrom;
    private Integer salaryTo;
    private LocalDate expiredDate;
    private Integer employeeQuantity;
    private Double requiredExperience;
    private Constant.Gender gender;
    private String workingPlace;
    private String description;
    private Constant.Status status;
    private Constant.Proposal proposed;
    private Constant.WorkingType workingType;
}
