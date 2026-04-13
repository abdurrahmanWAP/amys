package com.miniproject.amys.service.impl;

import com.miniproject.amys.constant.Message;
import com.miniproject.amys.dto.CreateResDto;
import com.miniproject.amys.dto.DeleteResDto;
import com.miniproject.amys.dto.UpdateResDto;
import com.miniproject.amys.dto.location.CreateLocationRequestDto;
import com.miniproject.amys.dto.location.LocationResponseDto;
import com.miniproject.amys.dto.location.UpdateLocationRequestDto;
import com.miniproject.amys.exception.DataIntegrityException;
import com.miniproject.amys.exception.DataVersionMismatchException;
import com.miniproject.amys.exception.NotFoundException;
import com.miniproject.amys.model.Company;
import com.miniproject.amys.model.Location;
import com.miniproject.amys.repository.CompanyRepo;
import com.miniproject.amys.repository.LocationRepo;
import com.miniproject.amys.service.BaseModelServicer;
import com.miniproject.amys.service.LocationService;
import jakarta.transaction.Transactional;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Profile("jpa")
@Service
public class LocationServiceImpl extends BaseModelServicer implements LocationService {
    private final LocationRepo locationRepo;
    private final CompanyRepo companyRepo;

    public LocationServiceImpl(
            LocationRepo locationRepo,
            CompanyRepo companyRepo) {
        this.locationRepo = locationRepo;
        this.companyRepo = companyRepo;
    }

    @Transactional
    @Override
    public CreateResDto insert(CreateLocationRequestDto requestDto) {
        Company company = companyRepo.findById(UUID.fromString(requestDto.getCompanyId()))
                .orElseThrow(() -> new NotFoundException("No Company Found"));

        if (locationRepo.findByLocationCode(requestDto.getCode()).isPresent()) {
            throw new DataIntegrityException("Location code already exist");
        }

        Location locationInsert = new Location();

        locationInsert.setLocationCode(requestDto.getCode());
        locationInsert.setLocationName(requestDto.getName());
        locationInsert.setCompany(company);

        createdAndId(locationInsert);
        Location location = locationRepo.save(locationInsert);
        return new CreateResDto(location.getId(), Message.CREATE.getMessage());
    }

    @Transactional
    @Override
    public List<LocationResponseDto> getAll() {
        return locationRepo.findAll().stream()
                .map(location -> responseDto(location))
                .toList();
    }

    @Transactional
    @Override
    public LocationResponseDto getLocation(String id) {
        Location location = locationRepo.findById(UUID.fromString(id))
                .orElseThrow(() -> new NotFoundException("No Location Found"));
        return responseDto(location);
    }

    @Transactional
    @Override
    public UpdateResDto update(String id, UpdateLocationRequestDto requestDto) {
        Company company = companyRepo.findById(UUID.fromString(requestDto.getCompanyId()))
                .orElseThrow(() -> new NotFoundException("No Company Found"));
        Location location = locationRepo.findById(UUID.fromString(id))
                .orElseThrow(() -> new NotFoundException("No Location Found"));
        if (!company.getCompanyCode().equals(requestDto.getCode())){
            if (locationRepo.findByLocationCode(requestDto.getCode()).isPresent()) {
                throw new DataIntegrityException("Location code already exist");
            }
        }

        if (location.getVersion().equals(requestDto.getVersion())) {
            location.setLocationCode(requestDto.getCode());
            location.setLocationName(requestDto.getName());
            location.setCompany(company);

            updated(location);
            Location locationUpdate = locationRepo.saveAndFlush(location);
            return new UpdateResDto(locationUpdate.getVersion(), Message.UPDATE.getMessage());
        }
        throw new DataVersionMismatchException("Refresh the page");
    }

    @Transactional
    @Override
    public DeleteResDto delete(String id) {
        Location location = locationRepo.findById(UUID.fromString(id))
                .orElseThrow(() -> new NotFoundException("No Location Found"));
        locationRepo.deleteById(location.getId());
        return new DeleteResDto(Message.DELETE.getMessage());
    }

    private LocationResponseDto responseDto(Location location) {
        return new LocationResponseDto(
                location.getId(),
                location.getLocationName(),
                location.getCompany().getCompanyName());
    }
}
