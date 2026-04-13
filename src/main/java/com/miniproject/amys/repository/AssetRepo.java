package com.miniproject.amys.repository;

import com.miniproject.amys.model.Asset;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AssetRepo extends JpaRepository<Asset, UUID> {

    Optional<Asset> findByAssetCode(String assetCode);
}
