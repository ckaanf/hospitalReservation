package com.example.hospitalreservation.users.service;

import com.example.hospitalreservation.users.dto.UserDto;
import com.example.hospitalreservation.users.dto.request.SignUpDto;
import jakarta.transaction.Transactional;

public interface UserService {

    @Transactional
    UserDto createUser(SignUpDto signUpDto);

    @org.springframework.transaction.annotation.Transactional(readOnly=true)
    UserDto getUser(String username);
}
