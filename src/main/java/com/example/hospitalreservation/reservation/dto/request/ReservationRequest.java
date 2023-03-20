package com.example.hospitalreservation.reservation.dto.request;

import com.example.hospitalreservation.reservation.dto.ReservationDto;
import com.example.hospitalreservation.users.dto.UserDto;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

public record ReservationRequest(
        String telNo,
        String reason,
        LocalDate reservationDate,
        List<MultipartFile> images
) {
    public static ReservationRequest of(String telNo,String reason,LocalDate reservationDate,List<MultipartFile> images){
        return new ReservationRequest( telNo, reason, reservationDate,images);
    }

    public ReservationDto toDto(UserDto user){
        return ReservationDto.of(
                user.id(),
                telNo,
                reason,
                reservationDate
        );
    }
}
