package com.miniproject.amys.controller;

import com.miniproject.amys.dto.auth.LoginRequestDto;
import com.miniproject.amys.dto.auth.LoginResponseDto;
import com.miniproject.amys.service.AuthService;
import com.miniproject.amys.util.JwtUtil;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@RestController
@RequestMapping("login")
public class AuthController {

    private final AuthService authService;
    private final AuthenticationManager authenticationManager;

    public AuthController(AuthService authService, AuthenticationManager authenticationManager) {
        this.authService = authService;
        this.authenticationManager=authenticationManager;
    }

    @PostMapping()
    public ResponseEntity<LoginResponseDto> login(@RequestBody @Valid LoginRequestDto request) {
        var auth = new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword());
        authenticationManager.authenticate(auth);

        var user = authService.findByUserEmail(request.getEmail());

        //generate token
        var token = JwtUtil.generateToken(
                user.getId().toString(),
                Timestamp.valueOf(LocalDateTime.now().plusHours(2)));


        LoginResponseDto res = new LoginResponseDto(
                user.getEmployee().getEmployeeName(),
                user.getRole().getRoleCode(),
                token
        );
        ResponseEntity response = new ResponseEntity(res, HttpStatus.OK);
        return response;

    }
}
