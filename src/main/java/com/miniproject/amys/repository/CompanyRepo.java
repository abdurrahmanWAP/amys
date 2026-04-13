package com.miniproject.amys.repository;

import com.miniproject.amys.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CompanyRepo extends JpaRepository<Company, UUID> {


    Optional<Company> findByCompanyCode(String companyCode);
}
