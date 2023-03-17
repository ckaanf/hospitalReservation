package com.example.hospitalreservation.reservation.dto;

import com.example.hospitalreservation.hospital.entity.Hospital;
import com.example.hospitalreservation.reservation.entity.Reservation;
import com.example.hospitalreservation.users.entity.Users;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public record ReservationDto(
        Long id,
        Long userId,
        Long hospitalId,

        String telNo,
        String reason,
        LocalDate reservationDate,

        LocalDateTime createdAt,
        LocalDateTime modifiedAt,

        List<ReasonImageDto> reasonImageDtos
) {
    public static ReservationDto of(Long id, Long userId , Long hospitalId, String telNo,String reason,LocalDate reservationDate,LocalDateTime createdAt,LocalDateTime modifiedAt, List<ReasonImageDto> reasonImageDtos){
        return new ReservationDto(id, userId , hospitalId, telNo, reason, reservationDate, createdAt, modifiedAt,reasonImageDtos);
    }

    public static ReservationDto of(Long hospitalId,Long userId, String telNo,String reason, LocalDate reservationDate){
        return new ReservationDto(null, hospitalId, userId, telNo, reason, reservationDate, null, null, null);
    }

    public static ReservationDto from(Reservation entity){
        return new ReservationDto(
                entity.getId(),
                entity.getUsers().getId(),
                entity.getHospital().getId(),
                entity.getTelNo(),
                entity.getReason(),
                entity.getReservationDate(),
                entity.getCreatedAt(),
                entity.getModifiedAt(),
                entity.getReasonImages().stream()
                        .map(ReasonImageDto::from)
                        .collect(Collectors.toList())
        );
    }

    public Reservation toEntity(Users users, Hospital hospital){
        return Reservation.of(
                telNo,
                reason,
                reservationDate,
                users,
                hospital
        );
    }
}
