package com.miniproject.amys.service.impl;

import com.miniproject.amys.constant.Message;
import com.miniproject.amys.dto.CreateResDto;
import com.miniproject.amys.dto.DeleteResDto;
import com.miniproject.amys.dto.UpdateResDto;
import com.miniproject.amys.dto.employee.CreateEmployeeRequestDto;
import com.miniproject.amys.dto.employee.EmployeeResponseDto;
import com.miniproject.amys.dto.employee.UpdateEmployeeRequestDto;
import com.miniproject.amys.exception.DataIntegrityException;
import com.miniproject.amys.exception.DataVersionMismatchException;
import com.miniproject.amys.exception.NotFoundException;
import com.miniproject.amys.model.Company;
import com.miniproject.amys.model.Employee;
import com.miniproject.amys.repository.CompanyRepo;
import com.miniproject.amys.repository.EmployeeRepo;
import com.miniproject.amys.service.BaseModelServicer;
import com.miniproject.amys.service.EmployeeService;
import jakarta.transaction.Transactional;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
@Profile("jpa")
@Service
public class EmployeeServiceImpl extends BaseModelServicer implements EmployeeService {

    private final EmployeeRepo employeeRepo;
    private final CompanyRepo companyRepo;

    public EmployeeServiceImpl(
            EmployeeRepo employeeRepo,
            CompanyRepo companyRepo) {
        this.employeeRepo = employeeRepo;
        this.companyRepo = companyRepo;
    }

    @Transactional
    @Override
    public CreateResDto insert(CreateEmployeeRequestDto data) {
        Company company = companyRepo.findById(UUID.fromString(data.getCompanyId()))
                .orElseThrow(() -> new NotFoundException("No Company Found"));

        if(employeeRepo.findByEmployeeCode(data.getCode()).isPresent()){
            throw new DataIntegrityException("Employee Code already exist");
        }

        if (employeeRepo.findByEmployeePhone(data.getPhone()).isPresent()){
            throw new DataIntegrityException("Employee Phone already exist");
        }

        Employee employeeInsert = new Employee();

        employeeInsert.setEmployeeCode(data.getCode());
        employeeInsert.setEmployeeName(data.getName());
        employeeInsert.setEmployeeAddress(data.getAddress());
        employeeInsert.setEmployeePhone(data.getPhone());
        employeeInsert.setCompany(company);

        createdAndId(employeeInsert);
        Employee employee = employeeRepo.save(employeeInsert);
        return new CreateResDto(employee.getId(), Message.CREATE.getMessage());
    }

    @Transactional
    @Override
    public UpdateResDto update(String id, UpdateEmployeeRequestDto requestDto) {
        Company company = companyRepo.findById(UUID.fromString(requestDto.getCompanyId()))
                .orElseThrow(() -> new NotFoundException("No Company Found"));
        Employee employee = employeeRepo.findById(UUID.fromString(id))
                .orElseThrow(() -> new NotFoundException("No Employee Found"));

        if (!employee.getEmployeeCode().equals(requestDto.getCode())){
            if(employeeRepo.findByEmployeeCode(requestDto.getCode()).isPresent()){
                throw new DataIntegrityException("Employee Code already exist");
            }
        }

        if(!employee.getEmployeePhone().equals(requestDto.getPhone())){
            if (employeeRepo.findByEmployeePhone(requestDto.getPhone()).isPresent()){
                throw new DataIntegrityException("Employee Phone already exist");
            }
        }

        if (employee.getVersion().equals(requestDto.getVersion())) {
            employee.setEmployeeName(requestDto.getName());
            employee.setEmployeeAddress(requestDto.getAddress());
            employee.setEmployeePhone(requestDto.getPhone());
            employee.setCompany(company);

            updated(employee);
            employeeRepo.saveAndFlush(employee);

            UpdateResDto updateResDto = new UpdateResDto(
                    employee.getVersion(),
                    Message.UPDATE.getMessage());
            return updateResDto;
        }
        throw new DataVersionMismatchException("Refresh the page");
    }

    @Transactional
    @Override
    public DeleteResDto delete(String id) {
        Employee employee = employeeRepo.findById(UUID.fromString(id)).orElseThrow(() -> new RuntimeException("No Employee Found"));
        employeeRepo.delete(employee);
        DeleteResDto deleteResDto = new DeleteResDto(Message.DELETE.getMessage());
        return deleteResDto;
    }

    @Transactional
    @Override
    public List<EmployeeResponseDto> getAll() {
        return employeeRepo.findAll().stream()
                .map(employee -> responseDto(employee))
                .toList();
    }

    @Transactional
    @Override
    public EmployeeResponseDto getById(String id) {
        Employee employee = employeeRepo.findById(UUID.fromString(id)).orElseThrow(
                () -> new NotFoundException("No Employee Found")
        );
        return responseDto(employee);
    }

    private EmployeeResponseDto responseDto(Employee employee) {
        return new EmployeeResponseDto(
                employee.getId(),
                employee.getEmployeeCode(),
                employee.getEmployeeName(),
                employee.getEmployeeAddress(),
                employee.getEmployeePhone(),
                employee.getCompany().getCompanyName()
        );
    }


}
