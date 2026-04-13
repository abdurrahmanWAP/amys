package com.miniproject.amys.service;

import com.miniproject.amys.dto.statusAssetType.StatusAssetResponseDto;

import java.util.List;

public interface StatusAssetService {
    List<StatusAssetResponseDto> getAllStatusAsset();

    StatusAssetResponseDto getStatusAsset(String id);
}
