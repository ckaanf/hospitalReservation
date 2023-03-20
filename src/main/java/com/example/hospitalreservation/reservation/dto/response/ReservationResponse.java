package com.example.hospitalreservation.reservation.dto.response;

import com.example.hospitalreservation.reservation.dto.ReasonImageDto;
import com.example.hospitalreservation.reservation.dto.ReservationDto;
import com.example.hospitalreservation.reservation.entity.Reservation;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public record ReservationResponse(
        Long id,
        Long hospitalId,
        String yadmNm,
        String addr,
        String hospitalTelNo,
        String dgsbjtCd,
        Long userId,
        String telNo,
        String reason,
        LocalDate reservationDate,
        List<ReasonImageDto> reasonImageDtos
) {
    public static ReservationResponse of(Long id,Long hospitalId,String yadmNm,String addr, String hospitalTelNo,String dgsbjtCd,Long userId,String telNo,String reason,LocalDate reservationDate,List<ReasonImageDto> reasonImageDtos){
        return new ReservationResponse(id, hospitalId, yadmNm, addr,hospitalTelNo,dgsbjtCd, userId, telNo, reason, reservationDate, reasonImageDtos);
    }

    public static ReservationResponse from(ReservationDto dto){
        return new ReservationResponse(
                dto.id(),
                dto.hospitalId(),
                null,
                null,
                null,
                null,
                dto.userId(),
                dto.telNo(),
                dto.reason(),
                dto.reservationDate(),
                dto.reasonImageDtos()
        );
    }
    public static ReservationResponse from(Reservation entity){
        return new ReservationResponse(
                entity.getId(),
                entity.getHospital().getId(),
                entity.getHospital().getYadmNm(),
                entity.getHospital().getAddr(),
                entity.getHospital().getTelno(),
                entity.getHospital().getDgsbjtCd(),
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
