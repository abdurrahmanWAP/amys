package com.miniproject.amys.service.unit;

import com.miniproject.amys.constant.Message;
import com.miniproject.amys.dto.assign.CreateAssignRequestDto;
import com.miniproject.amys.model.*;
import com.miniproject.amys.repository.*;
import com.miniproject.amys.service.impl.AssignServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
public class AssignService {
    @Mock
    private AssignRepo assignRepo;
    @Mock
    private AssignDetailRepo assignDetailRepo;
    @Mock
    private AssetRepo assetRepo;
    @Mock
    private LocationRepo locationRepo;
    @Mock
    private EmployeeRepo employeeRepo;

    @InjectMocks
    private AssignServiceImpl assignService;

    @Test
    public void shouldCreated_whenDataValid(){
        var companyId = UUID.randomUUID();

        var company = new Company();
        company.setId(companyId);
        company.setCompanyCode("DJY03");
        company.setCompanyName("DanS Jayaaaa");

        var assetTypeId = UUID.randomUUID();

        var assetType = new AssetType();
        assetType.setId(assetTypeId);
        assetType.setAssetTypeCode("AT001");
        assetType.setAssetTypeName("Laptop");


        var statusAssetTypeId = UUID.randomUUID();

        var statusAssetType = new StatusAssetType();
        statusAssetType.setId(statusAssetTypeId);
        statusAssetType.setStatusAssetTypeCode("ST001");
        statusAssetType.setStatusAssetTypeName("ACTIVE");

        var assetId = UUID.randomUUID();

        var asset = new Asset();
        asset.setId(assetId);
        asset.setAssetCode("AS001");
        asset.setAssetName("Laptop Developer");
        asset.setCompany(company);
        asset.setAssetType(assetType);
        asset.setStatusAssetType(statusAssetType);
        asset.setExpiredDate(LocalDate.now().plusYears(3));

        var employeeId = UUID.randomUUID();

        var employee = new Employee();
        employee.setId(employeeId);
        employee.setEmployeeCode("EMP001");
        employee.setEmployeeName("Budi Santoso");
        employee.setEmployeeAddress("Jakarta Selatan");
        employee.setEmployeePhone("081234567890");
        employee.setCompany(company);

        var locationId = UUID.randomUUID();

        var location = new Location();
        location.setId(locationId);
        location.setLocationCode("LOC001");
        location.setLocationName("Head Office Jakarta");
        location.setCompany(company);


        var id = UUID.randomUUID();
        var idString = id.toString();
        List<String> assetIdList = List.of(assetId.toString(),idString);

        var assignDto = new CreateAssignRequestDto();
        assignDto.setAssignCode("AS001");
        assignDto.setEmployeeTargetId(employeeId.toString());
        assignDto.setLocationTargetId(locationId.toString());
        assignDto.setAssetTargetId(assetId.toString());
        assignDto.setAssetIdList(assetIdList);

        var assignSaved = new Assign();
        assignSaved.setId(id);
        assignSaved.setAssignCode(assignDto.getAssignCode());
        assignSaved.setAssignDate(LocalDateTime.now());
        assignSaved.setEmployee(employee);
        assignSaved.setLocation(location);
        assignSaved.setAsset(asset);

        var assignDetailSaved = new AssignDetail();
        assignDetailSaved.setId(UUID.randomUUID());
        assignDetailSaved.setAssign(assignSaved);
        assignDetailSaved.setAsset(asset);

        Mockito.when(assetRepo.findById(Mockito.any()))
                .thenReturn(Optional.ofNullable(asset));
        Mockito.when(assignRepo.save(Mockito.any()))
                .thenReturn(assignSaved);
        Mockito.when(assignDetailRepo.save(Mockito.any()))
                .thenReturn(assignDetailSaved);

        var result = assignService.insert(assignDto);

        Assertions.assertEquals(id, result.getId());

        Mockito.verify(assignRepo, Mockito.atLeast(1))
                .save(Mockito.any());
        Mockito.verify(assetRepo, Mockito.atLeast(1))
                .findById(Mockito.any());
        Mockito.verify(assignDetailRepo, Mockito.atLeast(1))
                .save(Mockito.any());
    }

    @Test
    public void shouldUpdated_whenDataValid() {
        var companyId = UUID.randomUUID();

        var company = new Company();
        company.setId(companyId);
        company.setCompanyCode("DJY03");
        company.setCompanyName("DanS Jayaaaa");

        var assetTypeId = UUID.randomUUID();

        var assetType = new AssetType();
        assetType.setId(assetTypeId);
        assetType.setAssetTypeCode("AT001");
        assetType.setAssetTypeName("Laptop");


        var statusAssetTypeId = UUID.randomUUID();

        var statusAssetType = new StatusAssetType();
        statusAssetType.setId(statusAssetTypeId);
        statusAssetType.setStatusAssetTypeCode("ST001");
        statusAssetType.setStatusAssetTypeName("ACTIVE");

        var assetId = UUID.randomUUID();

        var asset = new Asset();
        asset.setId(assetId);
        asset.setAssetCode("AS001");
        asset.setAssetName("Laptop Developer");
        asset.setCompany(company);
        asset.setAssetType(assetType);
        asset.setStatusAssetType(statusAssetType);
        asset.setExpiredDate(LocalDate.now().plusYears(3));

        var assignId = UUID.randomUUID();

        var assign = new Assign();
        assign.setId(assignId);
        assign.setAssignCode("TRX001");
        assign.setAssignDate(LocalDateTime.now());
        assign.setAsset(asset);
        assign.setVersion(0);

        var assignDetailId = UUID.randomUUID();

        var assignDetail = new AssignDetail();
        assignDetail.setId(assignDetailId);
        assignDetail.setAssign(assign);
        assignDetail.setAsset(asset);

        var assignSaved = new Assign();
        assignDetail.setUpdatedAt(LocalDateTime.now());

        var assignDetailSaved = new AssignDetail();
        assignDetailSaved.setReturnDate(LocalDateTime.now());


        Mockito.when(assignRepo.findById(Mockito.any()))
                .thenReturn(Optional.ofNullable(assign));
        Mockito.when(assignDetailRepo.findById(Mockito.any()))
                .thenReturn(Optional.ofNullable(assignDetail));
        Mockito.when(assignDetailRepo.saveAndFlush(Mockito.any()))
                .thenReturn(assignDetailSaved);
        Mockito.when(assignRepo.saveAndFlush(Mockito.any()))
                .thenReturn(assignSaved);

        var result = assignService.returnAsset(assignId.toString(),List.of(assignDetailId.toString()));

        Assertions.assertEquals(Message.UPDATE.getMessage(), result.getMessage());

        Mockito.verify(assignRepo, Mockito.atLeast(1))
                .saveAndFlush(Mockito.any());
        Mockito.verify(assignDetailRepo, Mockito.atLeast(1))
                .saveAndFlush(Mockito.any());
        Mockito.verify(assignRepo, Mockito.atLeast(1))
                .findById(Mockito.any());
        Mockito.verify(assignDetailRepo, Mockito.atLeast(1))
                .findById(Mockito.any());

    }

    @Test
    public void shouldReturnData_whenIdValid(){
        var companyId = UUID.randomUUID();

        var company = new Company();
        company.setId(companyId);
        company.setCompanyCode("DJY03");
        company.setCompanyName("DanS Jayaaaa");

        var assetTypeId = UUID.randomUUID();

        var assetType = new AssetType();
        assetType.setId(assetTypeId);
        assetType.setAssetTypeCode("AT001");
        assetType.setAssetTypeName("Laptop");


        var statusAssetTypeId = UUID.randomUUID();

        var statusAssetType = new StatusAssetType();
        statusAssetType.setId(statusAssetTypeId);
        statusAssetType.setStatusAssetTypeCode("ST001");
        statusAssetType.setStatusAssetTypeName("ACTIVE");

        var assetId = UUID.randomUUID();

        var asset = new Asset();
        asset.setId(assetId);
        asset.setAssetCode("AS001");
        asset.setAssetName("Laptop Developer");
        asset.setCompany(company);
        asset.setAssetType(assetType);
        asset.setStatusAssetType(statusAssetType);
        asset.setExpiredDate(LocalDate.now().plusYears(3));

        var id = UUID.randomUUID();
        var assign = new Assign();
        assign.setId(id);
        assign.setAssignCode("AS001");
        assign.setAssignDate(LocalDateTime.now());
        assign.setAsset(asset);

        Mockito.when(assignRepo.findById(Mockito.any()))
                .thenReturn(Optional.ofNullable(assign));

        var result = assignService.getById(id.toString());

        Assertions.assertEquals(id.toString(), result.getId());

        Mockito.verify(assignRepo, Mockito.atLeast(1))
                .findById(id);

    }

    @Test
    public void shouldReturnData_whenDataValid(){
        var companyId = UUID.randomUUID();

        var company = new Company();
        company.setId(companyId);
        company.setCompanyCode("DJY03");
        company.setCompanyName("DanS Jayaaaa");

        var assetTypeId = UUID.randomUUID();

        var assetType = new AssetType();
        assetType.setId(assetTypeId);
        assetType.setAssetTypeCode("AT001");
        assetType.setAssetTypeName("Laptop");


        var statusAssetTypeId = UUID.randomUUID();

        var statusAssetType = new StatusAssetType();
        statusAssetType.setId(statusAssetTypeId);
        statusAssetType.setStatusAssetTypeCode("ST001");
        statusAssetType.setStatusAssetTypeName("ACTIVE");

        var assetId = UUID.randomUUID();

        var asset = new Asset();
        asset.setId(assetId);
        asset.setAssetCode("AS001");
        asset.setAssetName("Laptop Developer");
        asset.setCompany(company);
        asset.setAssetType(assetType);
        asset.setStatusAssetType(statusAssetType);
        asset.setExpiredDate(LocalDate.now().plusYears(3));

        var assignId = UUID.randomUUID();

        var assign = new Assign();
        assign.setId(assignId);
        assign.setAssignCode("TRX001");
        assign.setAssignDate(LocalDateTime.now());
        assign.setAsset(asset);
        assign.setVersion(0);

        var assignDetailId = UUID.randomUUID();

        var assignDetail = new AssignDetail();
        assignDetail.setId(assignDetailId);
        assignDetail.setAssign(assign);
        assignDetail.setAsset(asset);

        Mockito.when(assignRepo.findAll())
                .thenReturn(List.of(assign));

        var result = assignService.getAll();

        Assertions.assertEquals(List.of(assign).size(),result.size());

        Mockito.verify(assignRepo, Mockito.atLeast(1))
                .findAll();

    }
}
