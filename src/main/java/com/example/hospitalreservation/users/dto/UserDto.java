package com.example.hospitalreservation.users.dto;

import com.example.hospitalreservation.users.dto.request.SignUpDto;
import com.example.hospitalreservation.users.entity.Users;
import com.example.hospitalreservation.users.entity.UserRole;

import java.util.ArrayList;
import java.util.List;

public record UserDto(
        Long id,
        String userId,
        String name,
        String password,
        List<UserRole> userRoles
) {

    public static UserDto of(Long id,String userId,String name,String password,List<UserRole> userRoles){
        return new UserDto(
                id,
                userId,
                name,
                password,
                userRoles
        );
    }
    public static UserDto of(SignUpDto signUpDto, String encryptedPassword){
        return new UserDto(
                null,
                signUpDto.userId(),
                signUpDto.name(),
                encryptedPassword,
                new ArrayList<>()
        );
    }

    public static UserDto from(Users users){
        return new UserDto(
                users.getId(),
                users.getUserId(),
                users.getName(),
                users.getPassword(),
                users.getUserRoles()
        );
    }
    public Users toEntity(){
        return Users.of(
                userId,
                name,
                password,
                userRoles
        );
    }
}
