package com.miniproject.amys.dto.assetType;

import java.util.UUID;

public class AssetTypeResponseDto {
    private UUID id;
    private String code;
    private String name;

    public AssetTypeResponseDto() {
    }

    public AssetTypeResponseDto(UUID id, String code, String name) {
        this.id = id;
        this.code = code;
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
