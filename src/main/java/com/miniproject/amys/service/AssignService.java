package com.miniproject.amys.service;

import com.miniproject.amys.dto.CreateResDto;
import com.miniproject.amys.dto.UpdateResDto;
import com.miniproject.amys.dto.assign.AssignResponseDto;
import com.miniproject.amys.dto.assign.CreateAssignRequestDto;

import java.util.List;

public interface AssignService {

    List<AssignResponseDto> getAll();

    AssignResponseDto getById(String id);

    UpdateResDto returnAsset(String id, List<String> assignDetailIdList);

    CreateResDto insert(CreateAssignRequestDto requestDto);
}
