package com.miniproject.amys.service.impl;

import com.miniproject.amys.dto.role.RoleResponseDto;
import com.miniproject.amys.exception.NotFoundException;
import com.miniproject.amys.model.Role;
import com.miniproject.amys.repository.RoleRepo;
import com.miniproject.amys.service.RoleService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
@Profile("jpa")
@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepo roleRepo;

    @Autowired
    public RoleServiceImpl(RoleRepo roleRepo) {
        this.roleRepo = roleRepo;
    }

    @Transactional
    @Override
    public List<RoleResponseDto> getAll() {
        List<RoleResponseDto> result = roleRepo.findAll().stream()
                .map(role -> responseDto(role))
                .toList();
        return result;
    }

    @Transactional
    @Override
    public RoleResponseDto getById(String id) {
        Role role = roleRepo.findById(UUID.fromString(id)).orElseThrow(
                () -> new NotFoundException("No Role Found")
        );
        return responseDto(role);
    }

    private RoleResponseDto responseDto(Role role) {
        return new RoleResponseDto(role.getId(), role.getRoleCode(), role.getRoleName());
    }
}
