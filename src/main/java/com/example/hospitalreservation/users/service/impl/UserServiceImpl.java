package com.example.hospitalreservation.users.service.impl;

import com.example.hospitalreservation.users.constant.RoleType;
import com.example.hospitalreservation.users.dto.UserDto;
import com.example.hospitalreservation.users.dto.request.SignUpDto;
import com.example.hospitalreservation.users.entity.Users;
import com.example.hospitalreservation.users.entity.UserRole;
import com.example.hospitalreservation.users.repository.UserRepository;
import com.example.hospitalreservation.users.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public UserDto createUser(SignUpDto signUpDto){
        String encryptedPassword = passwordEncoder.encode(signUpDto.password());
        Users users = UserDto.of(signUpDto,encryptedPassword).toEntity();
        users.addUserRole(UserRole.of(RoleType.USER,users));

        return UserDto.from(userRepository.save(users));
    }
}
