package com.miniproject.amys.dto;

import java.util.UUID;

public class CreateResDto {
    private UUID id;
    private String message;

    public CreateResDto(){

    }

    public CreateResDto(UUID id, String message) {
        this.id = id;
        this.message = message;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


}
