package com.miniproject.amys.dto;

public class DeleteResDto {
    private String message;

    public DeleteResDto(){

    }

    public DeleteResDto(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
