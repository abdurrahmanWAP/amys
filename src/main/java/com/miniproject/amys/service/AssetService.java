package com.miniproject.amys.service;

import com.miniproject.amys.dto.CreateResDto;
import com.miniproject.amys.dto.DeleteResDto;
import com.miniproject.amys.dto.UpdateResDto;
import com.miniproject.amys.dto.asset.AssetResponseDto;
import com.miniproject.amys.dto.asset.CreateAssetRequestDto;
import com.miniproject.amys.dto.asset.UpdateAssetRequestDto;

import java.util.List;

public interface AssetService {





    CreateResDto insertAsset(CreateAssetRequestDto data);

    List<AssetResponseDto> getAllAsset();

    AssetResponseDto getAsset(String id);

    UpdateResDto updateAsset(String id, UpdateAssetRequestDto requestDto);

    DeleteResDto deleteAsset(String id);
}
