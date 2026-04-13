package com.miniproject.amys.controller;

import com.miniproject.amys.dto.CreateResDto;
import com.miniproject.amys.dto.DeleteResDto;
import com.miniproject.amys.dto.UpdateResDto;
import com.miniproject.amys.dto.user.CreateUserRequestDto;
import com.miniproject.amys.dto.user.UpdatePasswordRequestDto;
import com.miniproject.amys.dto.user.UpdateUserRequestDto;
import com.miniproject.amys.dto.user.UserResponseDto;
import com.miniproject.amys.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public ResponseEntity<List<UserResponseDto>> users(){
        List<UserResponseDto> res= userService.getAll();
        ResponseEntity response = new ResponseEntity(res, HttpStatus.OK);
        return response;
    }


    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> user(@PathVariable String id){
        UserResponseDto res = userService.getById(id);
        ResponseEntity response = new ResponseEntity(res, HttpStatus.OK);
        return response;
    }

    @PostMapping()
    public ResponseEntity<CreateResDto> createUser(@RequestBody @Valid CreateUserRequestDto request){
        CreateResDto res = userService.insert(request);
        ResponseEntity response = new ResponseEntity(res, HttpStatus.CREATED);
        return response;
    }

    @PutMapping("/{id}")
    public ResponseEntity<UpdateResDto> updateUser(@PathVariable String id, @RequestBody @Valid UpdateUserRequestDto request){
        UpdateResDto res = userService.update(id,request);
        ResponseEntity response = new ResponseEntity(res, HttpStatus.OK);
        return response;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DeleteResDto> deleteUser(@PathVariable String id){
        DeleteResDto res = userService.delete(id);
        ResponseEntity response = new ResponseEntity(res, HttpStatus.OK);
        return response;
    }

    @PatchMapping("/change-password")
    public UpdateResDto chagePasswordUser(@RequestBody UpdatePasswordRequestDto request){
        return null;
    }


}
