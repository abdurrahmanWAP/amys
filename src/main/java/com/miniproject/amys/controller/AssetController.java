package com.miniproject.amys.controller;

import com.miniproject.amys.dto.CreateResDto;
import com.miniproject.amys.dto.DeleteResDto;
import com.miniproject.amys.dto.UpdateResDto;
import com.miniproject.amys.dto.asset.AssetResponseDto;
import com.miniproject.amys.dto.asset.CreateAssetRequestDto;
import com.miniproject.amys.dto.asset.UpdateAssetRequestDto;
import com.miniproject.amys.service.AssetService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("assets")
public class AssetController {

    private final AssetService assetService;

    public AssetController(AssetService assetService) {
        this.assetService = assetService;
    }

    @GetMapping()
    public ResponseEntity<List<AssetResponseDto>> getAll() {
        List<AssetResponseDto> res = assetService.getAllAsset();
        ResponseEntity response = new ResponseEntity<>(res, HttpStatus.OK);
        return response;
    }

    @GetMapping("/{id}")
    public ResponseEntity<AssetResponseDto> asset(@PathVariable String id) {
        AssetResponseDto res = assetService.getAsset(id);
        ResponseEntity response = new ResponseEntity<>(res, HttpStatus.OK);
        return response;
    }

    @PostMapping()
    public ResponseEntity<CreateResDto> createAsset(@RequestBody @Valid CreateAssetRequestDto request) {
        CreateResDto res = assetService.insertAsset(request);
        ResponseEntity response = new ResponseEntity<>(res,HttpStatus.CREATED);
        return response;
    }

    @PutMapping("/{id}")
    public ResponseEntity<UpdateResDto> updateAsset(@PathVariable String id, @RequestBody @Valid UpdateAssetRequestDto requestDto) {
        UpdateResDto res = assetService.updateAsset(id,requestDto);
        ResponseEntity response = new ResponseEntity<>(res, HttpStatus.OK);
        return response;

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DeleteResDto> deleteAsset(@PathVariable String id) {
        DeleteResDto res = assetService.deleteAsset(id);
        ResponseEntity response = new ResponseEntity<>(res,HttpStatus.OK);
        return response;
    }
}
