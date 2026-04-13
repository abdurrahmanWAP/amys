package com.miniproject.amys.service;

import com.miniproject.amys.dto.CreateResDto;
import com.miniproject.amys.dto.DeleteResDto;
import com.miniproject.amys.dto.UpdateResDto;
import com.miniproject.amys.dto.user.CreateUserRequestDto;
import com.miniproject.amys.dto.user.UpdateUserRequestDto;
import com.miniproject.amys.dto.user.UserResponseDto;

import java.util.List;

public interface UserService {

    CreateResDto insert(CreateUserRequestDto data);

    UpdateResDto update(String id, UpdateUserRequestDto requestDto);

    DeleteResDto delete (String id);

    List<UserResponseDto> getAll();

    UserResponseDto getById(String id);

}
