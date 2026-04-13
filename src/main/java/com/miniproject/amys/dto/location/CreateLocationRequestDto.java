package com.miniproject.amys.dto.location;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CreateLocationRequestDto {
    @NotBlank(message = "Location code required")
    @Size(max = 20, message = "Location code Character limit exceeded")
    private String code;


    @NotBlank(message = "Location name required")
    @Size(max = 100, message = "Location name Character limit exceeded")
    private String name;

    @NotBlank(message = "Company required")
    private String companyId;

//    public CreateLocationRequestDto(String name, String companyId) {
//
//        this.name = name;
//        this.companyId = companyId;
//    }

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

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }
}
