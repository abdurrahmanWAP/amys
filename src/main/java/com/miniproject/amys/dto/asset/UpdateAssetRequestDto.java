package com.miniproject.amys.dto.asset;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UpdateAssetRequestDto {
    @NotBlank(message = "Asset code required")
    @Size(max = 20, message = "Asset Code Character limit exceeded")
    private String code;

    @NotBlank(message = "Asset name required")
    @Size(max = 100, message = "Asset Name Character limit exceeded")
    private String name;

    @NotBlank(message = "Company required")
    private String companyId;

    private String expiredDate;

    @NotNull(message = "Refresh the page")
    private Integer version;

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

    public String getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(String expiredDate) {
        this.expiredDate = expiredDate;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}
