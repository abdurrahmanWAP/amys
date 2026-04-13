package com.miniproject.amys.service;

import com.miniproject.amys.dto.CreateResDto;
import com.miniproject.amys.dto.DeleteResDto;
import com.miniproject.amys.dto.UpdateResDto;
import com.miniproject.amys.dto.employee.CreateEmployeeRequestDto;
import com.miniproject.amys.dto.employee.EmployeeResponseDto;
import com.miniproject.amys.dto.employee.UpdateEmployeeRequestDto;

import java.util.List;

public interface EmployeeService {
    CreateResDto insert(CreateEmployeeRequestDto data);

    UpdateResDto update(String id, UpdateEmployeeRequestDto requestDto);

    DeleteResDto delete(String id);

    List<EmployeeResponseDto> getAll();

    EmployeeResponseDto getById(String id);
}
