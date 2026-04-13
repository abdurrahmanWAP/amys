package com.miniproject.amys.controller;

import com.miniproject.amys.dto.CreateResDto;
import com.miniproject.amys.dto.UpdateResDto;
import com.miniproject.amys.dto.assign.AssignResponseDto;
import com.miniproject.amys.dto.assign.CreateAssignRequestDto;
import com.miniproject.amys.service.AssignService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("assigns")
public class AssignController {

    private final AssignService assignService;

    public AssignController(AssignService assignService) {
        this.assignService = assignService;
    }

    @GetMapping()
    public ResponseEntity<List<AssignResponseDto>> assignList() {
        List<AssignResponseDto> res = assignService.getAll();
        ResponseEntity response = new ResponseEntity<>(res, HttpStatus.OK);
        return response;
    }

    @GetMapping("/{id}")
    public ResponseEntity<AssignResponseDto> assign(@PathVariable String id) {
        AssignResponseDto res = assignService.getById(id);
        ResponseEntity response = new ResponseEntity<>(res, HttpStatus.OK);
        return response;
    }

    @PostMapping("/check-out")
    public ResponseEntity<CreateResDto> createAssign(@RequestBody @Valid CreateAssignRequestDto request) {
        CreateResDto res = assignService.insert(request);
        ResponseEntity response = new ResponseEntity<>(res, HttpStatus.CREATED);
        return response;
    }

    @PatchMapping("/{id}/check-in")
    public ResponseEntity<UpdateResDto> updateAssign(@PathVariable String id, @RequestBody List<String> assetIdList) {
        UpdateResDto res = assignService.returnAsset(id,assetIdList);
        ResponseEntity response = new ResponseEntity<>(res, HttpStatus.OK);
        return response;
    }

}
