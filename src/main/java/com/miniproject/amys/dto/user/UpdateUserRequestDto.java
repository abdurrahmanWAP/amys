package com.miniproject.amys.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UpdateUserRequestDto {
    @NotBlank(message = "Email required")
    @Email(message = "Email is not valid")
    @Size(max = 50, message = "Email Character limit exceeded")
    private String email;

    @NotBlank(message = "Employee required")
    private String employeeId;

    @NotBlank(message = "Role required")
    private String roleId;

    @NotNull(message = "Refresh the page")
    private Integer version;



    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}
