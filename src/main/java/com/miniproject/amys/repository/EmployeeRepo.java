package com.miniproject.amys.repository;

import com.miniproject.amys.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface EmployeeRepo extends JpaRepository<Employee, UUID> {
    Optional<Employee> findByEmployeeCode(String employeeCode);

    Optional<Employee> findByEmployeePhone(String employeePhone);
}
