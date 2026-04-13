package com.miniproject.amys.service.impl;

import com.miniproject.amys.constant.Message;
import com.miniproject.amys.dto.CreateResDto;
import com.miniproject.amys.dto.DeleteResDto;
import com.miniproject.amys.dto.UpdateResDto;
import com.miniproject.amys.dto.asset.AssetResponseDto;
import com.miniproject.amys.dto.asset.CreateAssetRequestDto;
import com.miniproject.amys.dto.asset.UpdateAssetRequestDto;
import com.miniproject.amys.exception.DataIntegrityException;
import com.miniproject.amys.exception.DataVersionMismatchException;
import com.miniproject.amys.exception.NotFoundException;
import com.miniproject.amys.model.Asset;
import com.miniproject.amys.model.AssetType;
import com.miniproject.amys.model.Company;
import com.miniproject.amys.model.StatusAssetType;
import com.miniproject.amys.repository.AssetRepo;
import com.miniproject.amys.repository.AssetStatusRepo;
import com.miniproject.amys.repository.AssetTypeRepo;
import com.miniproject.amys.repository.CompanyRepo;
import com.miniproject.amys.service.AssetService;
import com.miniproject.amys.service.BaseModelServicer;
import com.miniproject.amys.util.StringToDate;
import jakarta.transaction.Transactional;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Profile("jpa")
@Service
public class AssetServiceImpl extends BaseModelServicer implements AssetService {

    private final AssetRepo assetRepo;
    private final AssetTypeRepo assetTypeRepo;
    private final AssetStatusRepo assetStatusRepo;
    private final CompanyRepo companyRepo;

    public AssetServiceImpl(
            AssetRepo assetRepo,
            AssetTypeRepo assetTypeRepo,
            AssetStatusRepo assetStatusRepo,
            CompanyRepo companyRepo) {
        this.assetRepo = assetRepo;
        this.assetTypeRepo = assetTypeRepo;
        this.assetStatusRepo = assetStatusRepo;
        this.companyRepo = companyRepo;
    }

    @Transactional
    @Override
    public CreateResDto insertAsset(CreateAssetRequestDto requestDto) {
        Company company = companyRepo.findById(UUID.fromString(requestDto.getCompanyId()))
                .orElseThrow(() -> new NotFoundException("No Company Found"));
        AssetType assetType = assetTypeRepo.findById(UUID.fromString(requestDto.getTypeId()))
                .orElseThrow(() -> new NotFoundException("No Asset Type Found"));
        StatusAssetType statusAssetType = assetStatusRepo.findById(UUID.fromString(requestDto.getStatusId()))
                .orElseThrow(() -> new NotFoundException("No Asset Statuf Found"));


        if (assetRepo.findByAssetCode(requestDto.getCode()).isPresent()) {
            throw new DataIntegrityException("Asset Code Already exist");
        }
        Asset assetInsert = new Asset();

        assetInsert.setAssetCode(requestDto.getCode());
        assetInsert.setAssetName(requestDto.getName());
        assetInsert.setCompany(company);
        assetInsert.setAssetType(assetType);
        assetInsert.setStatusAssetType(statusAssetType);
        assetInsert.setExpiredDate(StringToDate.stringToDate(requestDto.getExpiredDate()));


        createdAndId(assetInsert);
        Asset asset = assetRepo.save(assetInsert);
        return new CreateResDto(asset.getId(), Message.CREATE.getMessage());
    }

    @Transactional
    @Override
    public List<AssetResponseDto> getAllAsset() {
        return assetRepo.findAll().stream()
                .map(asset -> assetToResponDto(asset))
                .toList();
    }

    @Transactional
    @Override
    public AssetResponseDto getAsset(String id) {
        Asset asset = assetRepo.findById(UUID.fromString(id))
                .orElseThrow(() -> new NotFoundException("No Asset Found"));
        return assetToResponDto(asset);
    }

    @Override
    public UpdateResDto updateAsset(String id, UpdateAssetRequestDto requestDto) {
        Company company = companyRepo.findById(UUID.fromString(requestDto.getCompanyId()))
                .orElseThrow(() -> new NotFoundException("No Company Found"));
        Asset asset = assetRepo.findById(UUID.fromString(id))
                .orElseThrow(() -> new NotFoundException("No Asset Found"));
        if (!asset.getAssetCode().equals(requestDto.getCode())) {
            if (assetRepo.findByAssetCode(requestDto.getCode()).isPresent()) {
                throw new DataIntegrityException("Asset Code already exist");
            }
        }
        if (asset.getVersion().equals(requestDto.getVersion())) {
            asset.setAssetCode(requestDto.getCode());
            asset.setAssetName(requestDto.getName());
            asset.setCompany(company);
            asset.setExpiredDate(StringToDate.stringToDate(requestDto.getExpiredDate()));

            updated(asset);
            assetRepo.saveAndFlush(asset);
            return new UpdateResDto(asset.getVersion(), Message.UPDATE.getMessage());
        }
        throw new DataVersionMismatchException("Refresh the page");
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public DeleteResDto deleteAsset(String id) {
        Asset asset = assetRepo.findById(UUID.fromString(id))
                .orElseThrow(() -> new NotFoundException("No Asset Found"));
        assetRepo.delete(asset);
        return new DeleteResDto(Message.DELETE.getMessage());
    }


    private AssetResponseDto assetToResponDto(Asset asset) {

        return new AssetResponseDto(
                asset.getId(),
                asset.getAssetCode(),
                asset.getAssetName(),
                asset.getCompany().getCompanyName(),
                asset.getAssetType().getAssetTypeName(),
                asset.getStatusAssetType().getStatusAssetTypeName(),
                asset.getExpiredDate());
    }


}
