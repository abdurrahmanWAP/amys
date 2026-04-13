package com.miniproject.amys.service.impl;

import com.miniproject.amys.constant.Message;
import com.miniproject.amys.dto.CreateResDto;
import com.miniproject.amys.dto.DeleteResDto;
import com.miniproject.amys.dto.UpdateResDto;
import com.miniproject.amys.dto.user.CreateUserRequestDto;
import com.miniproject.amys.dto.user.UpdateUserRequestDto;
import com.miniproject.amys.dto.user.UserResponseDto;
import com.miniproject.amys.exception.DataIntegrityException;
import com.miniproject.amys.exception.DataVersionMismatchException;
import com.miniproject.amys.exception.NotFoundException;
import com.miniproject.amys.model.Employee;
import com.miniproject.amys.model.Role;
import com.miniproject.amys.model.User;
import com.miniproject.amys.repository.EmployeeRepo;
import com.miniproject.amys.repository.RoleRepo;
import com.miniproject.amys.repository.UserRepo;
import com.miniproject.amys.service.BaseModelServicer;
import com.miniproject.amys.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
@Profile("jpa")
@Service
public class UserServiceImpl extends BaseModelServicer implements UserService {

    private final UserRepo userRepo;
    private final EmployeeRepo employeeRepo;
    private final RoleRepo roleRepo;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(
            UserRepo userRepo,
            EmployeeRepo employeeRepo,
            RoleRepo roleRepo,
            PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.employeeRepo = employeeRepo;
        this.roleRepo = roleRepo;
        this.passwordEncoder=passwordEncoder;
    }

    @Transactional
    @Override
    public CreateResDto insert(CreateUserRequestDto requestDto) {
        Employee employee = employeeRepo.findById(UUID.fromString(requestDto.getEmployeeId()))
                .orElseThrow(() -> new NotFoundException("No Employee Found"));
        Role role = roleRepo.findById(UUID.fromString(requestDto.getRoleId()))
                .orElseThrow(() -> new NotFoundException("No Role Found"));

        String password = passwordEncoder.encode(requestDto.getPassword());
        if (userRepo.findByUserEmail(requestDto.getEmail()).isPresent()){
            throw new DataIntegrityException("Email already exist");
        }

        User userInsert = new User();

        userInsert.setUserEmail(requestDto.getEmail());
        userInsert.setUserPassword(password);
        userInsert.setEmployee(employee);
        userInsert.setRole(role);

        createdAndId(userInsert);
        User user = userRepo.save(userInsert);
        return new CreateResDto(user.getId(), Message.CREATE.getMessage());
    }

    @Transactional
    @Override
    public UpdateResDto update(String id, UpdateUserRequestDto requestDto) {
        Employee employee = employeeRepo.findById(UUID.fromString(id))
                .orElseThrow(() -> new NotFoundException("No Employee Found"));

        Role role = roleRepo.findById(UUID.fromString(id))
                .orElseThrow(() -> new NotFoundException("No Role Found"));

        User user = userRepo.findById(UUID.fromString(id))
                .orElseThrow(() -> new NotFoundException("No User Found"));

        if (!user.getUserEmail().equals(requestDto.getEmail())){
            if (userRepo.findByUserEmail(requestDto.getEmail()).isPresent()){
                throw new DataIntegrityException("Email already exist");
            }
        }

        if (user.getVersion().equals(requestDto.getVersion())) {
            user.setUserEmail(requestDto.getEmail());
            user.setEmployee(employee);
            user.setRole(role);

            updated(user);
            userRepo.saveAndFlush(user);
            UpdateResDto updateResDto = new UpdateResDto(
                    user.getVersion(),
                    Message.UPDATE.getMessage());
            return updateResDto;
        }
        throw new DataVersionMismatchException("Refresh the page");
    }

    @Transactional
    @Override
    public DeleteResDto delete(String id) {
        User user = userRepo.findById(UUID.fromString(id))
                .orElseThrow(() -> new NotFoundException("No User Found"));
        userRepo.delete(user);
        DeleteResDto deleteResDto = new DeleteResDto(Message.DELETE.getMessage());
        return deleteResDto;
    }

    @Transactional
    @Override
    public List<UserResponseDto> getAll() {
        return userRepo.findAll().stream()
                .map(user -> ResponDto(user))
                .toList();
    }

    @Transactional
    @Override
    public UserResponseDto getById(String id) {
        User user = userRepo.findById(UUID.fromString(id)).orElseThrow(
                () -> new NotFoundException("No User Found")
        );
        return ResponDto(user);
    }


    private UserResponseDto ResponDto(User user) {
        return new UserResponseDto(user.getId(), user.getUserEmail(), user.getEmployee().getEmployeeName(), user.getRole().getRoleName());
    }
}
