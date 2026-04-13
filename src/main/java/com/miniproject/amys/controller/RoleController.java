package com.miniproject.amys.controller;

import com.miniproject.amys.dto.role.RoleResponseDto;
import com.miniproject.amys.service.RoleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("roles")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService){
        this.roleService=roleService;
    }

    @GetMapping()
    public ResponseEntity<List<RoleResponseDto>> getAll(){
        List<RoleResponseDto> res =roleService.getAll();
        ResponseEntity response = new ResponseEntity<>(res, HttpStatus.OK);
        return response;
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoleResponseDto> getById(@PathVariable String id){
        RoleResponseDto res = roleService.getById(id);
        ResponseEntity response = new ResponseEntity<>(res, HttpStatus.OK);
        return response;
    }
}
