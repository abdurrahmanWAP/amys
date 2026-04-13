package com.miniproject.amys.dto.assign;

public class AssignResponseDto {
    private String id;
    private String date;
    private String targetName;

    public AssignResponseDto(String id, String date, String targetName) {
        this.id = id;
        this.date = date;
        this.targetName = targetName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTargetName() {
        return targetName;
    }

    public void setTargetName(String targetName) {
        this.targetName = targetName;
    }
}
