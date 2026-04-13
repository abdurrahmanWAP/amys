package com.miniproject.amys.repository;

import com.miniproject.amys.model.AssetType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AssetTypeRepo extends JpaRepository<AssetType, UUID> {
}
