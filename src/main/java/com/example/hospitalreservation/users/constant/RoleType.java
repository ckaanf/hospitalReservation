package com.example.hospitalreservation.users.constant;

import lombok.Getter;

public enum RoleType {
    USER("ROLE_USER"),
    ADMIN("ROLE_ADMIN");

    @Getter
    private final String type;

    RoleType(String type) { this.type = type; }
}
