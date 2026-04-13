package com.miniproject.amys.controller;

import com.miniproject.amys.dto.statusAssetType.StatusAssetResponseDto;
import com.miniproject.amys.service.StatusAssetService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("status-asset-type")
public class StatusAssetController {

    private final StatusAssetService statusAssetService;

    public StatusAssetController(StatusAssetService statusAssetService) {
        this.statusAssetService = statusAssetService;
    }

    @GetMapping()
    public ResponseEntity<List<StatusAssetResponseDto>> statusAssets(){
        List<StatusAssetResponseDto> res = statusAssetService.getAllStatusAsset();
        ResponseEntity response = new ResponseEntity(res, HttpStatus.OK);
        return response;
    }

    @GetMapping("/{id}")
    public ResponseEntity<StatusAssetResponseDto> statusAsset(@PathVariable String id){
        StatusAssetResponseDto res = statusAssetService.getStatusAsset(id);
        ResponseEntity response = new ResponseEntity(res, HttpStatus.OK);
        return response;
    }

}
