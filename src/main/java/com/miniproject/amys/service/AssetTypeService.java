package com.miniproject.amys.service;

import com.miniproject.amys.dto.assetType.AssetTypeResponseDto;

import java.util.List;

public interface AssetTypeService {
    List<AssetTypeResponseDto> getAllAssetType();

    AssetTypeResponseDto getAssetType(String id);
}
