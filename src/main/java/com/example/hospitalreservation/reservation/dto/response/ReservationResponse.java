package com.example.hospitalreservation.reservation.dto.response;

import com.example.hospitalreservation.reservation.dto.ReasonImageDto;
import com.example.hospitalreservation.reservation.entity.Reservation;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public record ReservationResponse(
        Long id,
        Long hospitalId,
        Long userId,
        String telNo,
        String reason,
        LocalDate reservationDate,
        List<ReasonImageDto> reasonImageDtos
) {
    public static ReservationResponse of(Long id,Long hospitalId,Long userId,String telNo,String reason,LocalDate reservationDate,List<ReasonImageDto> reasonImageDtos){
        return new ReservationResponse(id, hospitalId, userId, telNo, reason, reservationDate, reasonImageDtos);
    }

    public static ReservationResponse from(Reservation entity){
        return new ReservationResponse(
                entity.getId(),
                entity.getHospital().getId(),
                entity.getUsers().getId(),
                entity.getTelNo(),
                entity.getReason(),
                entity.getReservationDate(),
                entity.getReasonImages().stream()
                        .map(ReasonImageDto::from)
                        .collect(Collectors.toList())
        );
    }
}
