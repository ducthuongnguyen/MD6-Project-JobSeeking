package com.codegym.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RecruitmentNewsFilterParam {
    private String title;
    private Integer salaryFrom;
    private Integer salaryTo;
    private String workingPlace;
}
