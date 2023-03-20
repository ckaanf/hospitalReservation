package com.example.hospitalreservation.users.service.impl;

import com.example.hospitalreservation.users.constant.RoleType;
import com.example.hospitalreservation.users.dto.UserDto;
import com.example.hospitalreservation.users.dto.request.SignUpDto;
import com.example.hospitalreservation.users.entity.UserRole;
import com.example.hospitalreservation.users.entity.Users;
import com.example.hospitalreservation.users.repository.UserRepository;
import com.example.hospitalreservation.users.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional(readOnly=true)
    @Override
    public UserDto getUser(String username){
        return userRepository.findByUsername(username)
                .map(UserDto::from)
                .orElseThrow(() -> new EntityNotFoundException("회원이 없습니다."));
    }
}
