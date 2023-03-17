package com.example.hospitalreservation.config.security.dto;

public record CustomPrincipal(
        Long id,
        Long userId,
        String username,
        String name
) {
    public static CustomPrincipal of (Long id,Long userId,String username,String name){
        return new CustomPrincipal(id,userId,username, name);
    }
}
