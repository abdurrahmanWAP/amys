package com.miniproject.amys.service.impl;

import com.miniproject.amys.dto.statusAssetType.StatusAssetResponseDto;
import com.miniproject.amys.exception.NotFoundException;
import com.miniproject.amys.model.StatusAssetType;
import com.miniproject.amys.repository.AssetStatusRepo;
import com.miniproject.amys.service.StatusAssetService;
import jakarta.transaction.Transactional;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
@Profile("jpa")
@Service
public class StatusAssetServiceImpl implements StatusAssetService {
    private final AssetStatusRepo assetStatusRepo;

    public StatusAssetServiceImpl(AssetStatusRepo assetStatusRepo) {
        this.assetStatusRepo = assetStatusRepo;
    }

    @Transactional
//    @Cacheable(value = "status-asset", key = "'all'")
    @Override
    public List<StatusAssetResponseDto> getAllStatusAsset() {
        return assetStatusRepo.findAll().stream()
                .map(statusAssetType -> statusToResponDto(statusAssetType))
                .toList();
    }

    @Transactional
    @Override
    public StatusAssetResponseDto getStatusAsset(String id) {
        StatusAssetType statusAssetType = assetStatusRepo.findById(UUID.fromString(id))
                .orElseThrow(() -> new NotFoundException("No Status Asset Found"));
        return statusToResponDto(statusAssetType);
    }

    private StatusAssetResponseDto statusToResponDto(StatusAssetType statusAssetType) {
        return new StatusAssetResponseDto(
                statusAssetType.getId(),
                statusAssetType.getStatusAssetTypeCode(),
                statusAssetType.getStatusAssetTypeName());
    }
}
