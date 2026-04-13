package com.miniproject.amys.repository;

import com.miniproject.amys.model.AssignDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AssignDetailRepo extends JpaRepository<AssignDetail, UUID> {
}
