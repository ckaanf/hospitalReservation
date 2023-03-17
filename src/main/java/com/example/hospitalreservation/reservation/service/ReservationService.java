package com.example.hospitalreservation.reservation.service;

import com.example.hospitalreservation.config.security.dto.CustomPrincipal;
import com.example.hospitalreservation.reservation.dto.ReservationDto;
import com.example.hospitalreservation.reservation.dto.request.ReservationRequest;
import com.example.hospitalreservation.reservation.entity.Reservation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ReservationService {
    //예약 단건 조회
    @Transactional(readOnly = true)
    Reservation readReservation(CustomPrincipal principal, Long reservationId);

    //userId 로 예약리스트 get
    @Transactional(readOnly = true)
    List<ReservationDto> readReservationList(CustomPrincipal principal);

    //예약하기
    Reservation createReservation(ReservationRequest reservationRequest, CustomPrincipal principal, Long hospitalId, List<MultipartFile> multipartFiles) throws IOException;

    //예약수정
    Reservation updateReservation(ReservationRequest reservationRequest, CustomPrincipal principal, Long reservationId);

    //예약삭제
    void deleteReservation(CustomPrincipal principal, Long hospitalId, Long reservationId);
}
