package com.miniproject.amys.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "t_asset")
public class Asset extends BaseModel {

    @Column(nullable = false, length = 20, unique = true)
    private String assetCode;

    @Column(nullable = false, length = 100)
    private String assetName;

    @ManyToOne
    @JoinColumn(name = "asset_company_id", nullable = false)
    private Company company;

    @ManyToOne
    @JoinColumn(name = "asset_type_id", nullable = false)
    private AssetType assetType;

    @ManyToOne
    @JoinColumn(name = "asset_status_id", nullable = false)
    private StatusAssetType statusAssetType;

    @Column
    private LocalDate expiredDate;

    public String getAssetCode() {
        return assetCode;
    }

    public void setAssetCode(String assetCode) {
        this.assetCode = assetCode;
    }

    public String getAssetName() {
        return assetName;
    }

    public void setAssetName(String assetName) {
        this.assetName = assetName;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public AssetType getAssetType() {
        return assetType;
    }

    public void setAssetType(AssetType assetType) {
        this.assetType = assetType;
    }

    public StatusAssetType getStatusAssetType() {
        return statusAssetType;
    }

    public void setStatusAssetType(StatusAssetType statusAssetType) {
        this.statusAssetType = statusAssetType;
    }

    public LocalDate getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(LocalDate expiredDate) {
        this.expiredDate = expiredDate;
    }
}
