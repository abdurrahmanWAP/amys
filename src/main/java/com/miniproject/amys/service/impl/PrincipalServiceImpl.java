package com.miniproject.amys.service.impl;

import com.miniproject.amys.pojo.AuthorizationPojo;
import com.miniproject.amys.service.PrincipalService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class PrincipalServiceImpl implements PrincipalService {
    @Override
    public AuthorizationPojo getPrincipal() {
        var auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth== null){
            throw new UsernameNotFoundException("Invalid Login");
        }
        return (AuthorizationPojo) auth.getPrincipal();
    }
}
