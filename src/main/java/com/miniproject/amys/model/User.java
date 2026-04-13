package com.miniproject.amys.model;

import jakarta.persistence.*;

@Entity
@Table(name = "t_user")
public class User extends BaseModel {

    @Column(nullable = false, length = 50, unique = true)
    private String userEmail;

    @Column(nullable = false, length = 255)
    private String userPassword;

    @OneToOne
    @JoinColumn(name = "user_employee_id", nullable = false)
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "user_role_id", nullable = false)
    private Role role;

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
