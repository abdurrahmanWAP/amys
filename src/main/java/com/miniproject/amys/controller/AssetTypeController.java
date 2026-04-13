package com.miniproject.amys.controller;

import com.miniproject.amys.dto.assetType.AssetTypeResponseDto;
import com.miniproject.amys.service.AssetTypeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("assets-types")
public class AssetTypeController {

    private final AssetTypeService assetTypeService;

    public AssetTypeController(AssetTypeService assetTypeService) {

        this.assetTypeService = assetTypeService;
    }

    @GetMapping()
    public ResponseEntity<List<AssetTypeResponseDto>> assetTypes(){
        List<AssetTypeResponseDto> res = assetTypeService.getAllAssetType();
        ResponseEntity response = new ResponseEntity<>(res, HttpStatus.OK);
        return response;
    }

    @GetMapping("/{id}")
    public ResponseEntity<AssetTypeResponseDto> assetType(@PathVariable String id){
        AssetTypeResponseDto res = assetTypeService.getAssetType(id);
        ResponseEntity response = new ResponseEntity<>(res,HttpStatus.OK);
        return response;
    }
}
