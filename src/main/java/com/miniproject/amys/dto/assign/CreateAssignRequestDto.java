package com.miniproject.amys.dto.assign;

import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public class CreateAssignRequestDto {

    private String assignCode;

    private String assetTargetId;
    private String locationTargetId;
    private String employeeTargetId;

    @NotEmpty(message = "Asset Id required")
    private List<String> assetIdList;

    public String getAssignCode() {
        return assignCode;
    }

    public void setAssignCode(String assignCode) {
        this.assignCode = assignCode;
    }

    public String getAssetTargetId() {
        return assetTargetId;
    }

    public void setAssetTargetId(String assetTargetId) {
        this.assetTargetId = assetTargetId;
    }

    public String getLocationTargetId() {
        return locationTargetId;
    }

    public void setLocationTargetId(String locationTargetId) {
        this.locationTargetId = locationTargetId;
    }

    public String getEmployeeTargetId() {
        return employeeTargetId;
    }

    public void setEmployeeTargetId(String employeeTargetId) {
        this.employeeTargetId = employeeTargetId;
    }

    public List<String> getAssetIdList() {
        return assetIdList;
    }

    public void setAssetIdList(List<String> assetIdList) {
        this.assetIdList = assetIdList;
    }
}
