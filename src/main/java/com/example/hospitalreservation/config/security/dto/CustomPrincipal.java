package com.example.hospitalreservation.config.security.dto;

public record CustomPrincipal(
        String userId,
        String name
) {
    public static CustomPrincipal of (String userId,String name){
        return new CustomPrincipal(userId, name);
    }
}
