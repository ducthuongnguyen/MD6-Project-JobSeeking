package com.codegym.model.dto.response;

public class CompanyResponse {
    private Long id;
    private String companyCode;
    private String name;
    private String avatar;

    public CompanyResponse() {
    }

    public CompanyResponse(Long id, String companyCode, String name, String avatar) {
        this.id = id;
        this.companyCode = companyCode;
        this.name = name;
        this.avatar = avatar;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
