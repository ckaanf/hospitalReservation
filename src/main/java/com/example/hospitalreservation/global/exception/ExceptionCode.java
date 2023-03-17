package com.example.hospitalreservation.global.exception;

import lombok.Getter;

public enum ExceptionCode {
    UNAUTHORIZED(401, "UNAUTHORIZED"),
    HOSPITAL_NOT_FOUND(404,"Hospital not found"),
    USER_NOT_FOUND(404,"User not found"),
    RESERVATION_NOT_FOUND(404,"Reservation not found"),
    USERNAME_EXISTS(405, "UserName exist"),
    NAME_EXISTS(405, "Name exist");

    @Getter
    private final int status;

    @Getter
    private final String message;

    ExceptionCode(int status, String message){
        this.status = status;
        this.message = message;
    }
}
