package com.miniproject.amys.model;

import jakarta.persistence.*;

@Entity
@Table(name = "t_employee")
public class Employee extends BaseModel {

    @Column(nullable = false, length = 20, unique = true)
    private String employeeCode;

    @Column(nullable = false, length = 50)
    private String employeeName;

    @Column(nullable = false, length = 100)
    private String employeeAddress;

    @Column(nullable = false, length = 16, unique = true)
    private String employeePhone;

    @ManyToOne
    @JoinColumn(name = "employee_company_id", nullable = false)
    private Company company;

    public String getEmployeeCode() {
        return employeeCode;
    }

    public void setEmployeeCode(String employeeCode) {
        this.employeeCode = employeeCode;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeeAddress() {
        return employeeAddress;
    }

    public void setEmployeeAddress(String employeeAddress) {
        this.employeeAddress = employeeAddress;
    }

    public String getEmployeePhone() {
        return employeePhone;
    }

    public void setEmployeePhone(String employeePhone) {
        this.employeePhone = employeePhone;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
