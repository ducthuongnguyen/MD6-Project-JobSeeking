package com.codegym.model.entity;

import com.codegym.constant.Constant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
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
    @NotBlank
    private String title;
    @ManyToOne
    private Company company;
    @ManyToOne
    private Vacancy vacancy;
    @ManyToOne
    private Field field;
    @NotBlank
    private Integer salaryFrom;
    @NotBlank
    private Integer salaryTo;
    @NotBlank
    private LocalDate expiredDate;
    @NotBlank
    private Integer employeeQuantity;
    @NotBlank
    private Double requiredExperience;
    @NotBlank
    private Constant.Gender gender;
    @NotBlank
    private String workingPlace;
    @NotBlank
    private String description;
    @NotBlank
    private Constant.Status status;
    @NotBlank
    private Constant.Proposal proposed;
    @NotBlank
    private Constant.WorkingType workingType;
}
