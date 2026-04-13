package com.miniproject.amys.dto.employee;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UpdateEmployeeRequestDto {
    @NotBlank(message = "Employee code required")
    @Size(max = 20, message = "Employee code Character limit exceeded")
    private String code;

    @NotBlank(message = "Employee name required")
    @Size(max = 50, message = "Employee Name Character limit exceeded")
    private String name;

    @NotBlank(message = "Employee address required")
    @Size(max = 100, message = "Employee Address Character limit exceeded")
    private String address;

    @NotBlank(message = "Employee phone required")
    @Size(max = 16, message = "Employee Phone Character limit exceeded")
    private String phone;

    @NotBlank(message = "Company required")
    private String companyId;

    @NotNull(message = "Refresh the page")
    private Integer version;

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}
