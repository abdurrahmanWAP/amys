package com.miniproject.amys.service.unit;

import com.miniproject.amys.dto.location.CreateLocationRequestDto;
import com.miniproject.amys.dto.location.LocationResponseDto;
import com.miniproject.amys.dto.location.UpdateLocationRequestDto;
import com.miniproject.amys.model.Company;
import com.miniproject.amys.model.Location;
import com.miniproject.amys.repository.CompanyRepo;
import com.miniproject.amys.repository.LocationRepo;
import com.miniproject.amys.service.impl.LocationServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
public class LocationService {

    @Mock
    private CompanyRepo companyRepo;
    @Mock
    private LocationRepo locationRepo;

    @InjectMocks
    private LocationServiceImpl locationService;

    @Test
    public void shouldCreated_whenDataValid(){
        var id = UUID.randomUUID();
        var companyId = UUID.randomUUID();

        var locationDto = new CreateLocationRequestDto();
        locationDto.setCode("L001");
        locationDto.setName("DAnS Kantor");
        locationDto.setCompanyId(companyId.toString());

        var locationSaved = new Location();
        locationSaved.setId(id);
        locationSaved.setLocationCode(locationDto.getCode());


        Mockito.when(companyRepo.findById(Mockito.any())).thenReturn(Optional.ofNullable(new Company()));
        Mockito.when(locationRepo.save(Mockito.any())).thenReturn(locationSaved);

        var result = locationService.insert(locationDto);

        Assertions.assertEquals(id,result.getId());
        Assertions.assertEquals(companyId, UUID.fromString(locationDto.getCompanyId()));

        Mockito.verify(locationRepo, Mockito.atLeast(1)).save(Mockito.any());
        Mockito.verify(companyRepo, Mockito.atLeast(1)).findById(Mockito.any());
    }

    @Test
    public void shouldUpdated_whenDataValid(){
        var id = UUID.randomUUID();
        var companyId = UUID.randomUUID();

        var company = new Company();
        company.setId(companyId);
        company.setCompanyCode("DJY03");
        company.setCompanyName("DanS Jayaaaa");

        var location = new Location();
        location.setId(id);
        location.setLocationCode("L001");
        location.setLocationName("DAnS Kantor");
        location.setCompany(company);
        location.setVersion(0);

        var locationDto = new UpdateLocationRequestDto();
        locationDto.setCode("L001");
        locationDto.setName("DAnS Kantor");
        locationDto.setCompanyId(company.getId().toString());
        locationDto.setVersion(0);

        var locationSaved = new Location();
        locationSaved.setLocationCode(locationDto.getCode());
        locationSaved.setLocationName(locationDto.getName());
        locationSaved.setVersion(1);

        Mockito.when(companyRepo.findById(Mockito.any()))
                .thenReturn(Optional.ofNullable(company));
        Mockito.when(locationRepo.findById(Mockito.any()))
                .thenReturn(Optional.ofNullable(location));
        Mockito.when(locationRepo.saveAndFlush(Mockito.any()))
                .thenReturn(locationSaved);

        var result = locationService.update(id.toString(), locationDto);

        Assertions.assertEquals(1, result.getVersion());

        Mockito.verify(companyRepo, Mockito.atLeast(1))
                .findById(Mockito.any());
        Mockito.verify(locationRepo, Mockito.atLeast(1))
                .findById(Mockito.any());
        Mockito.verify(locationRepo, Mockito.atLeast(1))
                .saveAndFlush(Mockito.any());

    }

    @Test
    public void shouldReturnData_whenIdValid(){
        var company = new Company();
        company.setCompanyName("DanS Jayaaaa");

        var id = UUID.randomUUID();
        var locationSaved = new Location();
        locationSaved.setId(id);
        locationSaved.setLocationCode("L001");
        locationSaved.setLocationName("DAnS Kantor");
        locationSaved.setCompany(company);

        Mockito.when(locationRepo.findById(Mockito.any()))
                .thenReturn(Optional.ofNullable(locationSaved));

        var result = locationService.getLocation(id.toString());

        Assertions.assertEquals("DAnS Kantor",result.getName());
        Mockito.verify(locationRepo, Mockito.atLeast(1)).findById(id);
    }

    @Test
    public void shouldReturnData_whenDataValid(){
        var id = UUID.randomUUID();
        var companyId = UUID.randomUUID();

        var company = new Company();
        company.setId(companyId);
        company.setCompanyCode("DJY03");
        company.setCompanyName("DanS Jayaaaa");

        var location = new Location();
        location.setId(id);
        location.setLocationCode("L001");
        location.setLocationName("DAnS Kantor");
        location.setCompany(company);
        location.setVersion(0);

        Mockito.when(locationRepo.findAll())
                .thenReturn(List.of(location));

        var result = locationService.getAll();

        Assertions.assertEquals(List.of(location).size(),result.size());
        Mockito.verify(locationRepo,Mockito.atLeast(1))
                .findAll();

    }

}
