package com.codegym.model.entity;

import com.codegym.constant.Constant;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Date;

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
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date expiredDate;
    private Integer employeeQuantity;
    private Double requiredExperience;
    private Constant.Gender gender;
    private Long workingPlace;
    private String description;
    private Constant.Status status;
    private Constant.Proposal proposed;
    private Constant.WorkingType workingType;
}
