package com.example.hospitalreservation.config.security.dto;


public record CustomPrincipal(
        Long userId,
        String username,
        String name
) {
    public static CustomPrincipal of (Long userId,String username,String name){
        return new CustomPrincipal(userId,username,name);
    }
}
