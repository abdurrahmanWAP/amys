package com.miniproject.amys.dto.asset;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CreateAssetRequestDto {
    @NotBlank(message = "Asset code required")
    @Size(max = 20, message = "Asset Code Character limit exceeded")
    private String code;

    @NotBlank(message = "Asset name required")
    @Size(max = 100, message = "Asset Name Character limit exceeded")
    private String name;

    @NotBlank(message = "Company required")
    private String companyId;

    @NotBlank(message = "Asset Type required")
    private String typeId;

    @NotBlank(message = "Asset Status required")
    private String statusId;

    private String expiredDate;

    public CreateAssetRequestDto(String name, String companyId, String typeId, String statusId, String expiredDate) {
        this.name = name;
        this.companyId = companyId;
        this.typeId = typeId;
        this.statusId = statusId;
        this.expiredDate = expiredDate;
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

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getStatusId() {
        return statusId;
    }

    public void setStatusId(String statusId) {
        this.statusId = statusId;
    }

    public String getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(String expiredDate) {
        this.expiredDate = expiredDate;
    }
}
