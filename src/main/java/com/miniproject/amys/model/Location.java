package com.miniproject.amys.model;

import jakarta.persistence.*;

@Entity
@Table(name = "t_location")
public class Location extends BaseModel {

    @Column(nullable = false, length = 20, unique = true)
    private String locationCode;

    @Column(nullable = false, length = 100)
    private String locationName;

    @ManyToOne
    @JoinColumn(name = "location_company_id", nullable = false)
    private Company company;

    public String getLocationCode() {
        return locationCode;
    }

    public void setLocationCode(String locationCode) {
        this.locationCode = locationCode;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
