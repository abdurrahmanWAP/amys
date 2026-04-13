package com.miniproject.amys.service.impl;

import com.miniproject.amys.dto.auth.LoginRequestDto;
import com.miniproject.amys.dto.auth.LoginResponseDto;
import com.miniproject.amys.exception.NotFoundException;
import com.miniproject.amys.repository.UserRepo;
import com.miniproject.amys.service.AuthService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AuthServiceImpl implements AuthService {
    private final UserRepo userRepo;

    public AuthServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public LoginResponseDto login(LoginRequestDto responseDto) {
        return null;
    }

    @Override
    public com.dansmultipro.ams.model.User findByUserEmail(String email) {
        return userRepo.findByUserEmail(email)
                .orElseThrow(()-> new NotFoundException("No User found"));
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        var userDB = userRepo.findByUserEmail(email)
                .orElseThrow(()-> new UsernameNotFoundException(email));
        return new User(
                email, userDB.getUserPassword(), new ArrayList<>()
        );
    }
}
