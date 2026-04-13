package com.miniproject.amys.service.impl;

import com.miniproject.amys.dto.assetType.AssetTypeResponseDto;
import com.miniproject.amys.exception.NotFoundException;
import com.miniproject.amys.model.AssetType;
import com.miniproject.amys.repository.AssetTypeRepo;
import com.miniproject.amys.service.AssetTypeService;
import jakarta.transaction.Transactional;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@Profile("jpa")
@Service
public class AssetTypeServiceImpl implements AssetTypeService {

    private AssetTypeRepo assetTypeRepo;

    public AssetTypeServiceImpl(AssetTypeRepo assetTypeRepo) {
        this.assetTypeRepo = assetTypeRepo;
    }

    @Transactional
    @Cacheable(value = "asset-type", key = "'all'")
    @Override
    public List<AssetTypeResponseDto> getAllAssetType() {
        List<AssetType> assetTypes = assetTypeRepo.findAll();
        List<AssetTypeResponseDto> responseList = new ArrayList<>();

        for (AssetType assetType : assetTypes) {
            AssetTypeResponseDto dto = typeToResponDto(assetType);
            responseList.add(dto);
        }

        return responseList;
    }

    @Transactional
    @Override
    public AssetTypeResponseDto getAssetType(String id) {
        AssetType assetType = assetTypeRepo.findById(UUID.fromString(id))
                .orElseThrow(() -> new NotFoundException("No Asset Type Found"));
        return typeToResponDto(assetType);
    }

    private AssetTypeResponseDto typeToResponDto(AssetType assetType) {
        return new AssetTypeResponseDto(
                assetType.getId(),
                assetType.getAssetTypeCode(),
                assetType.getAssetTypeName());
    }
}
