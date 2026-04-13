package com.miniproject.amys.controller;

import com.miniproject.amys.dto.CreateResDto;
import com.miniproject.amys.dto.DeleteResDto;
import com.miniproject.amys.dto.UpdateResDto;
import com.miniproject.amys.dto.company.CompanyResponseDto;
import com.miniproject.amys.dto.company.CreateCompanyRequestDto;
import com.miniproject.amys.dto.company.UpdateCompanyRequestDto;
import com.miniproject.amys.service.CompanyService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("companies")
public class CompanyController {
    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping()
    public ResponseEntity<List<CompanyResponseDto>> allCompanies(){
        List<CompanyResponseDto> res = companyService.getAll();
        ResponseEntity response = new ResponseEntity<>(res, HttpStatus.OK);
        return response;
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompanyResponseDto> company(@PathVariable String id){
        CompanyResponseDto res = companyService.getById(id);
        ResponseEntity response = new ResponseEntity<>(res, HttpStatus.OK);
        return response;
    }

    @PostMapping()
    public ResponseEntity<CreateResDto> createResDto(@RequestBody @Valid CreateCompanyRequestDto requestDto){
        CreateResDto res = companyService.insert(requestDto);
        ResponseEntity response = new ResponseEntity<>(res, HttpStatus.CREATED);
        return response;
    }

    @PutMapping("/{id}")
    public ResponseEntity<UpdateResDto> updateResDto(@PathVariable String id, @RequestBody @Valid UpdateCompanyRequestDto requestDto){
        UpdateResDto res = companyService.update(id, requestDto);
        ResponseEntity response = new ResponseEntity<>(res, HttpStatus.OK);
        return response;
    }

    @DeleteMapping("{id}")
    public ResponseEntity<DeleteResDto> deleteResDto(@PathVariable String id){
        DeleteResDto res = companyService.delete(id);
        ResponseEntity response = new ResponseEntity<>(res, HttpStatus.OK);
        return response;
    }
}
