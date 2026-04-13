package com.miniproject.amys.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "t_asset_type")
public class AssetType extends BaseModel {

    @Column(nullable = false, length = 20, unique = true)
    private String assetTypeCode;

    @Column(nullable = false,length = 20,unique = true)
    private String assetTypeName;

    public String getAssetTypeCode() {
        return assetTypeCode;
    }

    public void setAssetTypeCode(String assetTypeCode) {
        this.assetTypeCode = assetTypeCode;
    }

    public String getAssetTypeName() {
        return assetTypeName;
    }

    public void setAssetTypeName(String assetTypeName) {
        this.assetTypeName = assetTypeName;
    }
}
