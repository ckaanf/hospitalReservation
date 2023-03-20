package com.example.hospitalreservation.users.dto.response;

import com.example.hospitalreservation.users.dto.UserDto;

public record UserResponse(
        Long id,
        String username,
        String name
) {
    public static UserResponse of(Long id, String username, String name){
        return new UserResponse(id, username, name);
    }

    public static UserResponse from(UserDto dto){
        return new UserResponse(
                dto.id(),
                dto.username(),
                dto.name()
        );
    }
}
