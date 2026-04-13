package com.miniproject.amys.controller;

import com.miniproject.amys.dto.CreateResDto;
import com.miniproject.amys.dto.DeleteResDto;
import com.miniproject.amys.dto.UpdateResDto;
import com.miniproject.amys.dto.employee.CreateEmployeeRequestDto;
import com.miniproject.amys.dto.employee.EmployeeResponseDto;
import com.miniproject.amys.dto.employee.UpdateEmployeeRequestDto;
import com.miniproject.amys.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping()
    public ResponseEntity<List<EmployeeResponseDto>> employess() {
        List<EmployeeResponseDto> res = employeeService.getAll();
        ResponseEntity response = new ResponseEntity<>(res, HttpStatus.OK);
        return response;
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponseDto> employee(@PathVariable String id) {
        EmployeeResponseDto res = employeeService.getById(id);
        ResponseEntity response = new ResponseEntity<>(res, HttpStatus.OK);
        return response;
    }

    @PostMapping()
    public ResponseEntity<CreateResDto> createEmployee(@RequestBody @Valid CreateEmployeeRequestDto requestDto) {
        CreateResDto res = employeeService.insert(requestDto);
        ResponseEntity response = new ResponseEntity<>(res, HttpStatus.CREATED);
        return response;
    }

    @PutMapping("/{id}")
    public ResponseEntity<UpdateResDto> updateEmployee(@PathVariable String id, @RequestBody @Valid UpdateEmployeeRequestDto requestDto) {
        UpdateResDto res = employeeService.update(id, requestDto);
        ResponseEntity response = new ResponseEntity<>(res, HttpStatus.OK);
        return response;
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<DeleteResDto> deleteEmployee(@PathVariable String id) {
        DeleteResDto res = employeeService.delete(id);
        ResponseEntity response = new ResponseEntity<>(res, HttpStatus.OK);
        return response;
    }
}
