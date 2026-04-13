package com.miniproject.amys.dto;

public class UpdateResDto {
    private Integer version;
    private String message;

    public UpdateResDto(){

    }

    public UpdateResDto(Integer version, String message) {
        this.version = version;
        this.message = message;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
