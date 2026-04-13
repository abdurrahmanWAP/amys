package com.miniproject.amys.repository;

import com.miniproject.amys.model.Assign;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AssignRepo extends JpaRepository<Assign, UUID> {

    Optional<Assign> findByAssignCode(String assignCode);
}
