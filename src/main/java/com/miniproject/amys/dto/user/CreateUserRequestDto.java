package com.miniproject.amys.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CreateUserRequestDto {
    @NotBlank(message = "Email required")
    @Email(message = "Email is not valid")
    @Size(max = 50, message = "Email Character limit exceeded")
    private String email;

    @NotBlank(message = "Password required")
    @Size(max = 255, message = "Password Character limit exceeded")
    private String password;

    @NotBlank(message = "Employee required")
    private String employeeId;

    @NotBlank(message = "Role required")
    private String roleId;

    public CreateUserRequestDto(String email, String password, String employeeId, String roleId) {
        this.email = email;
        this.password = password;
        this.employeeId = employeeId;
        this.roleId = roleId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
}
