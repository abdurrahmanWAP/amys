package com.miniproject.amys.constant;

public enum StatusType {
    DEPLOYABLE("DEP"),
    UNDEPLOYABLE("UND") ,
    ARCHIVED("ARC");

    private String code;

    StatusType(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
