package com.example.hospitalreservation.reservation.dto;

import com.example.hospitalreservation.reservation.entity.ReasonImage;
import com.example.hospitalreservation.reservation.entity.Reservation;

public record ReasonImageDto(
        Long id,
        Long reservationId,
        String imgUrl
) {
    public static ReasonImageDto of(Long id,Long reservationId,String imgUrl){
        return new ReasonImageDto(id, reservationId, imgUrl);
    }

    public static ReasonImageDto from(ReasonImage entity){
        return new ReasonImageDto(
                entity.getId(),
                entity.getReservation().getId(),
                entity.getImgUrl()
        );
    }

    public ReasonImage toEntity(Reservation reservation){
        return ReasonImage.of(
                imgUrl,
                reservation
        );
    }
}
