package com.miniproject.amys.service;

import com.miniproject.amys.dto.role.RoleResponseDto;

import java.util.List;

public interface RoleService {
    List<RoleResponseDto> getAll();

    RoleResponseDto getById(String id);

}
