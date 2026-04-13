package com.miniproject.amys.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "t_status_asset_type")
public class StatusAssetType extends  BaseModel {

    @Column(nullable = false, length = 20, unique = true)
    private String statusAssetTypeCode;

    @Column(nullable = false, length = 20, unique = true)
    private String statusAssetTypeName;

    public String getStatusAssetTypeCode() {
        return statusAssetTypeCode;
    }

    public void setStatusAssetTypeCode(String statusAssetTypeCode) {
        this.statusAssetTypeCode = statusAssetTypeCode;
    }

    public String getStatusAssetTypeName() {
        return statusAssetTypeName;
    }

    public void setStatusAssetTypeName(String statusAssetTypeName) {
        this.statusAssetTypeName = statusAssetTypeName;
    }
}
