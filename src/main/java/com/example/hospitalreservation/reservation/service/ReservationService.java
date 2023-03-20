package com.example.hospitalreservation.reservation.service;

import com.example.hospitalreservation.config.security.dto.CustomPrincipal;
import com.example.hospitalreservation.reservation.dto.request.ReservationRequest;
import com.example.hospitalreservation.reservation.dto.response.ReservationResponse;
import com.example.hospitalreservation.reservation.entity.Reservation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ReservationService {
    //예약 단건 조회
    @Transactional(readOnly = true)
    ReservationResponse readReservation(String username, Long reservationId);

    //userId 로 예약리스트 get
    @Transactional(readOnly = true)
    Page<ReservationResponse> readReservationList(String username, Pageable pageable);

    //예약하기
    Reservation createReservation(ReservationRequest reservationRequest, String username, Long hospitalId, List<MultipartFile> multipartFiles) throws IOException;

    //예약수정
    Reservation updateReservation(ReservationRequest reservationRequest, String username, Long reservationId ,List<MultipartFile> multipartFiles) throws IOException;

    //예약삭제
    void deleteReservation(String username, Long reservationId);
}
