package com.miniproject.amys.service.impl;

import com.miniproject.amys.constant.Message;
import com.miniproject.amys.dto.CreateResDto;
import com.miniproject.amys.dto.DeleteResDto;
import com.miniproject.amys.dto.UpdateResDto;
import com.miniproject.amys.dto.company.CompanyResponseDto;
import com.miniproject.amys.dto.company.CreateCompanyRequestDto;
import com.miniproject.amys.dto.company.UpdateCompanyRequestDto;
import com.miniproject.amys.exception.DataIntegrityException;
import com.miniproject.amys.exception.DataVersionMismatchException;
import com.miniproject.amys.exception.NotFoundException;
import com.miniproject.amys.model.Company;
import com.miniproject.amys.repository.CompanyRepo;
import com.miniproject.amys.service.BaseModelServicer;
import com.miniproject.amys.service.CompanyService;
import jakarta.transaction.Transactional;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Profile("jpa")
@Service
public class CompanyServiceImpl extends BaseModelServicer implements CompanyService {
    private final CompanyRepo companyRepo;

    public CompanyServiceImpl(CompanyRepo companyRepo) {
        this.companyRepo = companyRepo;
    }

    @Transactional
    @CacheEvict(value = "companies", allEntries = true)
    @Override
    public CreateResDto insert(CreateCompanyRequestDto requestDto) {
        if (companyRepo.findByCompanyCode(requestDto.getCode()).isPresent()) {
            throw new DataIntegrityException("Company Code Already exist");
        }
        Company companyInsert = new Company();

        companyInsert.setCompanyCode(requestDto.getCode());
        companyInsert.setCompanyName(requestDto.getName());

        createdAndId(companyInsert);
        Company company = companyRepo.save(companyInsert);

        return new CreateResDto(company.getId(), Message.CREATE.getMessage());
    }

    @Transactional
    @CacheEvict(value = "companies", key = "#id")
    @Override
    public UpdateResDto update(String id, UpdateCompanyRequestDto requestDto) {
        Company company = companyRepo.findById(UUID.fromString(id))
                .orElseThrow(() -> new NotFoundException("No Company Found"));

        if (!company.getCompanyCode().equals(requestDto.getCode())) {
            if (companyRepo.findByCompanyCode(requestDto.getCode()).isPresent()) {
                throw new DataIntegrityException("Company Code Already exist");
            }
        }

        if (company.getVersion().equals(requestDto.getVersion())) {
            company.setCompanyCode(requestDto.getCode());
            company.setCompanyName(requestDto.getName());

            updated(company);
            companyRepo.saveAndFlush(company);
            UpdateResDto updateResDto = new UpdateResDto(
                    company.getVersion(),
                    Message.UPDATE.getMessage());

            return updateResDto;
        }
        throw new DataVersionMismatchException("Refresh the page");
    }

    @Transactional
    @CacheEvict(value = "companies", allEntries = true)
    @Override
    public DeleteResDto delete(String id) {
        Company company = companyRepo.findById(UUID.fromString(id)).orElseThrow(
                () -> new NotFoundException("No Company Found")
        );
        companyRepo.delete(company);
        DeleteResDto deleteResDto = new DeleteResDto(Message.DELETE.getMessage());
        return deleteResDto;
    }

    @Transactional
    @Cacheable(value = "companies", key = "'id'")
    @Override
    public List<CompanyResponseDto> getAll() {
        List<Company> companies = companyRepo.findAll();
        List<CompanyResponseDto> companyResponseDtos = new ArrayList<>();

        for (Company company : companies){
            CompanyResponseDto res = responseDto(company);
            companyResponseDtos.add(res);
        }
        return companyResponseDtos;

//        return companyRepo.findAll().stream()
//                .map(company -> responseDto(company))
//                .toList();
    }

    @Transactional
    @Override
    public CompanyResponseDto getById(String id) {
        Company company = companyRepo.findById(UUID.fromString(id)).orElseThrow(
                () -> new NotFoundException("No Company Found")
        );
        return responseDto(company);
    }

    private CompanyResponseDto responseDto(Company company) {
        return new CompanyResponseDto(
                company.getId(),
                company.getCompanyCode(),
                company.getCompanyName());
    }
}
