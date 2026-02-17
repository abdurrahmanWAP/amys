package com.miniproject.amys.constant;

public enum RoleType {
    SUPER_ADMIN("SA"),
    ASSET_MANAGER("AM");

    private String code;

    RoleType(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
