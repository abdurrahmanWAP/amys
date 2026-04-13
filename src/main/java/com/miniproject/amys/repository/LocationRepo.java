package com.miniproject.amys.repository;

import com.miniproject.amys.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface LocationRepo extends JpaRepository<Location, UUID> {
    Optional<Location> findByLocationCode(String locationCode);
}
