package com.miniproject.amys.dto.user;

import jakarta.validation.constraints.NotBlank;

public class UpdatePasswordRequestDto {
    @NotBlank(message = "Password required")
    private String oldPassword;

    @NotBlank(message = "New Password required")
    private String newPpassword;

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPpassword() {
        return newPpassword;
    }

    public void setNewPpassword(String newPpassword) {
        this.newPpassword = newPpassword;
    }
}
