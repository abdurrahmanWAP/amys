package com.miniproject.amys.repository;

import com.miniproject.amys.model.StatusAssetType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AssetStatusRepo extends JpaRepository<StatusAssetType, UUID> {
}
