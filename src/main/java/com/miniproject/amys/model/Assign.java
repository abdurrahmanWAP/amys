package com.miniproject.amys.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "t_assign")
public class Assign extends BaseModel {

    @Column(nullable = false, length = 20, unique = true)
    private String assignCode;

    @Column(nullable = false)
    private LocalDateTime assignDate;

    @ManyToOne
    @JoinColumn(name = "target_employee_id")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "target_asset_id")
    private Asset asset;

    @ManyToOne
    @JoinColumn(name = "target_location_id")
    private Location location;

    public String getAssignCode() {
        return assignCode;
    }

    public void setAssignCode(String assignCode) {
        this.assignCode = assignCode;
    }

    public LocalDateTime getAssignDate() {
        return assignDate;
    }

    public void setAssignDate(LocalDateTime assignDate) {
        this.assignDate = assignDate;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Asset getAsset() {
        return asset;
    }

    public void setAsset(Asset asset) {
        this.asset = asset;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
