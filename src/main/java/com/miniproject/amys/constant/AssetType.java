package com.miniproject.amys.constant;

public enum AssetType {
    GENERAL("GEN"),
    LICENSES("LIN"),
    COMPONENTS("COMP"),
    CONSUMABLES("CONS");

    private String code;

    AssetType(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
