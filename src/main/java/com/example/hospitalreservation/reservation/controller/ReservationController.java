package com.example.hospitalreservation.reservation.controller;

import com.example.hospitalreservation.config.security.dto.CustomPrincipal;
import com.example.hospitalreservation.reservation.dto.request.ReservationRequest;
import com.example.hospitalreservation.reservation.dto.response.ReservationResponse;
import com.example.hospitalreservation.reservation.entity.Reservation;
import com.example.hospitalreservation.reservation.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/reservation")
@RestController
public class ReservationController {
    private final ReservationService reservationService;

    //예약 상세 조회
    @GetMapping("/{reservation-id}")
    public ResponseEntity getReservation(
            @AuthenticationPrincipal CustomPrincipal principal,
            @PathVariable("reservation-id") Long reservationId){
        ReservationResponse reservation = ReservationResponse.from(reservationService.readReservation(principal, reservationId));

        return new ResponseEntity<>(
                new ResponseEntity<>(reservation, HttpStatus.OK).getStatusCode()
        );
    }

    //예약
    @PostMapping(value = "/{hospital-id}/reservation", consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity postReservation(
            @PathVariable("hospital-id") Long hospitalId,
            @AuthenticationPrincipal CustomPrincipal principal,
            @RequestPart(value = "request")ReservationRequest reservationRequest,
            @RequestPart(value = "images",required = false) List<MultipartFile> images) throws IOException{

        Reservation reservation = reservationService.createReservation(reservationRequest, principal, hospitalId, images);

        return new ResponseEntity<>(
                new ResponseEntity<>(reservation, HttpStatus.OK).getStatusCode()
        );
    }

}
