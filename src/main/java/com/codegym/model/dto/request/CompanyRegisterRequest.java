package com.codegym.model.dto.request;

import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.UniqueElements;

import javax.validation.constraints.NotBlank;

@Data
@Builder
public class CompanyRegisterRequest {
    @NotBlank(message = "Email must not be blank")
    @UniqueElements(message = "Email must be unique")
    private String email;
    @NotBlank(message = "Password must not be blank")
    private String password;
}
