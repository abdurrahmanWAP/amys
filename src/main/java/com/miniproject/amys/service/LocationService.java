package com.miniproject.amys.service;

import com.miniproject.amys.dto.CreateResDto;
import com.miniproject.amys.dto.DeleteResDto;
import com.miniproject.amys.dto.UpdateResDto;
import com.miniproject.amys.dto.location.CreateLocationRequestDto;
import com.miniproject.amys.dto.location.LocationResponseDto;
import com.miniproject.amys.dto.location.UpdateLocationRequestDto;

import java.util.List;

public interface LocationService {

    CreateResDto insert(CreateLocationRequestDto requestDto);

    List<LocationResponseDto> getAll();

    LocationResponseDto getLocation(String id);

    UpdateResDto update(String id, UpdateLocationRequestDto requestDto);

    DeleteResDto delete(String id);
}
