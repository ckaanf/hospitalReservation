package com.example.hospitalreservation.reservation.dto.request;

import com.example.hospitalreservation.reservation.dto.ReservationDto;
import com.example.hospitalreservation.users.dto.UserDto;

import java.time.LocalDate;

public record ReservationRequest(
        Long hospitalId,
        String telNo,
        String reason,
        LocalDate reservationDate
) {
    public static ReservationRequest of(Long hospitalId, String telNo,String reason,LocalDate reservationDate){
        return new ReservationRequest(hospitalId, telNo, reason, reservationDate);
    }

    public ReservationDto toDto(UserDto user){
        return ReservationDto.of(
                hospitalId,
                user.id(),
                telNo,
                reason,
                reservationDate
        );
    }
}
