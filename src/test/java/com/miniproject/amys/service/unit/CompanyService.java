package com.miniproject.amys.service.unit;

import com.miniproject.amys.dto.company.CreateCompanyRequestDto;
import com.miniproject.amys.dto.company.UpdateCompanyRequestDto;
import com.miniproject.amys.model.Company;
import com.miniproject.amys.repository.CompanyRepo;
import com.miniproject.amys.service.impl.CompanyServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
public class CompanyService {

    @Mock
    private CompanyRepo companyRepo;

    @InjectMocks
    private CompanyServiceImpl companyService;

    @Test
    public void shouldCreated_whenDataValid(){
        var companyDto = new CreateCompanyRequestDto();
        companyDto.setCode("DA");
        companyDto.setName("DAnS Academy");

        var id = UUID.randomUUID();
        var companySaved = new Company();
        companySaved.setId(id);

        Mockito.when(companyRepo.save(Mockito.any()))
                .thenReturn(companySaved);

        var result = companyService.insert(companyDto);

        Assertions.assertEquals(id, result.getId());

        Mockito.verify(companyRepo,Mockito.atLeast(1))
                .save(Mockito.any());

    }

    @Test
    public void shouldUpdated_whenDataValid(){
        var id = UUID.randomUUID();

        var company = new Company();
        company.setId(id);
        company.setCompanyCode("DJY03");
        company.setCompanyName("DanS Jayaaaa");
        company.setVersion(0);

        var companyDto = new UpdateCompanyRequestDto();
        companyDto.setCode("DJY03");
        companyDto.setName("Pujiii");
        companyDto.setVersion(0);

        var companySaved = new Company();
        companySaved.setCompanyCode(companyDto.getCode());

        Mockito.when(companyRepo.findById(Mockito.any()))
                .thenReturn(Optional.ofNullable(company));
        Mockito.when(companyRepo.saveAndFlush(Mockito.any()))
                .thenReturn(companySaved);

        var result = companyService.update(id.toString(), companyDto);

        Assertions.assertEquals(companyDto.getVersion(), result.getVersion());

        Mockito.verify(companyRepo, Mockito.atLeast(1))
                .findById(Mockito.any());
        Mockito.verify(companyRepo, Mockito.atLeast(1))
                .saveAndFlush(Mockito.any());
    }

    @Test
    public void shouldReturnData_whenIdValid(){
        var id = UUID.randomUUID();
        var companySaved = new Company();
        companySaved.setId(id);
        companySaved.setCompanyCode("DA");
        companySaved.setCompanyName("DAnS Academy");

        Mockito.when(companyRepo.findById(Mockito.any()))
                .thenReturn(Optional.ofNullable(companySaved));

        var result = companyService.getById(id.toString());

        Assertions.assertEquals("DAnS Academy", result.getName());
        Mockito.verify(companyRepo,Mockito.atLeast(1)).findById(id);

    }
}
