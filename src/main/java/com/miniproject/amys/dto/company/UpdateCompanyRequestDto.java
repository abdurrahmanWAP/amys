package com.miniproject.amys.dto.company;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UpdateCompanyRequestDto {
    @NotBlank(message = "Company code required")
    @Size(max = 10,message = "Company code Character limit exceeded")
    private String code;

    @NotBlank(message = "Company name required")
    @Size(max = 25, message = "Company name Character limit exceeded")
    private  String name;

    @NotNull(message = "Refresh the page")
    private Integer version;

    public UpdateCompanyRequestDto(){

    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}
