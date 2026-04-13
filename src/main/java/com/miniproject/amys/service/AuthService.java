package com.miniproject.amys.service;

import com.miniproject.amys.dto.auth.LoginRequestDto;
import com.miniproject.amys.dto.auth.LoginResponseDto;
import com.miniproject.amys.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AuthService extends UserDetailsService {
    LoginResponseDto login(LoginRequestDto responseDto);

    User findByUserEmail(String email);
}
