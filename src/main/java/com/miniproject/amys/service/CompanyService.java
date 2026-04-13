package com.miniproject.amys.service;

import com.miniproject.amys.dto.CreateResDto;
import com.miniproject.amys.dto.DeleteResDto;
import com.miniproject.amys.dto.UpdateResDto;
import com.miniproject.amys.dto.company.CompanyResponseDto;
import com.miniproject.amys.dto.company.CreateCompanyRequestDto;
import com.miniproject.amys.dto.company.UpdateCompanyRequestDto;

import java.util.List;

public interface CompanyService {

    CreateResDto insert(CreateCompanyRequestDto data);

    UpdateResDto update(String id, UpdateCompanyRequestDto requestDto);

    DeleteResDto delete(String id);

    List<CompanyResponseDto> getAll();

    CompanyResponseDto getById(String id);


}
