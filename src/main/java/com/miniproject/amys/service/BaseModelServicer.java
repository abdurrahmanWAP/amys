package com.miniproject.amys.service;

import com.miniproject.amys.model.BaseModel;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.UUID;

public abstract class BaseModelServicer {
    protected PrincipalService principalService;

    @Autowired
    private void setPrincipalService(PrincipalService principalService){
        this.principalService=principalService;
    }

    public <T extends BaseModel> void createdAndId(T entity) {
        entity.setId(UUID.randomUUID());
        entity.setCreatedAt(LocalDateTime.now());
        entity.setCreatedBy(UUID.fromString(principalService.getPrincipal().getId()));
    }


    public <T extends BaseModel> void updated(T entity) {
        entity.setUpdatedAt(LocalDateTime.now());
        entity.setUpdatedBy(UUID.fromString(principalService.getPrincipal().getId()));
    }
}
