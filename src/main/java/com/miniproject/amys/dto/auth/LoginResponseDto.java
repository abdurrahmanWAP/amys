package com.miniproject.amys.dto.auth;

public class LoginResponseDto {
    private String name;
    private String roleCode;
    private String token;

    public LoginResponseDto(String name, String roleCode, String token) {
        this.name = name;
        this.roleCode = roleCode;
        this.token = token;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
