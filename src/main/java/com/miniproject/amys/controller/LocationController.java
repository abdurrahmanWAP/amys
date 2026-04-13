package com.miniproject.amys.controller;

import com.miniproject.amys.dto.CreateResDto;
import com.miniproject.amys.dto.DeleteResDto;
import com.miniproject.amys.dto.UpdateResDto;
import com.miniproject.amys.dto.location.CreateLocationRequestDto;
import com.miniproject.amys.dto.location.LocationResponseDto;
import com.miniproject.amys.dto.location.UpdateLocationRequestDto;
import com.miniproject.amys.service.LocationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("locations")
public class LocationController {

    private final LocationService locationService;

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @GetMapping()
    public ResponseEntity<List<LocationResponseDto>> locations() {
        List<LocationResponseDto> res = locationService.getAll();
        ResponseEntity response = new ResponseEntity<>(res, HttpStatus.OK);
        return response;
    }

    @GetMapping("/{id}")
    public ResponseEntity<LocationResponseDto> location(@PathVariable String id) {
        LocationResponseDto res = locationService.getLocation(id);
        ResponseEntity response = new ResponseEntity<>(res, HttpStatus.OK);
        return response;
    }

    @PostMapping()
    public ResponseEntity<CreateResDto> createLocation(@RequestBody @Valid CreateLocationRequestDto request) {
        CreateResDto res = locationService.insert(request);
        ResponseEntity response = new ResponseEntity<>(res, HttpStatus.CREATED);
        return response;
    }

    @PutMapping("/{id}")
    public ResponseEntity<UpdateResDto> updateLocation(@PathVariable String id, @RequestBody @Valid UpdateLocationRequestDto request) {
        UpdateResDto res = locationService.update(id, request);
        ResponseEntity response = new ResponseEntity<>(res, HttpStatus.OK);
        return response;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DeleteResDto> deleteLocation(@PathVariable String id) {
        DeleteResDto res = locationService.delete(id);
        ResponseEntity response = new ResponseEntity<>(res, HttpStatus.OK);
        return response;
    }
}
