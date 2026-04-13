package com.miniproject.amys.dto.asset;

import java.time.LocalDate;
import java.util.UUID;

public class AssetResponseDto {
    private UUID id;
    private String code;
    private String name;
    private String companyName;
    private String typeName;
    private String statusName;
    private LocalDate expiredDate;

    public AssetResponseDto(UUID id, String code, String name, String companyName, String typeName, String statusName, LocalDate expiredDate) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.companyName = companyName;
        this.typeName = typeName;
        this.statusName = statusName;
        this.expiredDate = expiredDate;
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

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public LocalDate getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(LocalDate expiredDate) {
        this.expiredDate = expiredDate;
    }
}
