package com.example.hospitalreservation.reservation.service.impl;

import com.example.hospitalreservation.config.security.dto.CustomPrincipal;
import com.example.hospitalreservation.global.fileupload.S3Uploader;
import com.example.hospitalreservation.hospital.entity.Hospital;
import com.example.hospitalreservation.hospital.repository.HospitalRepository;
import com.example.hospitalreservation.reservation.dto.ReservationDto;
import com.example.hospitalreservation.reservation.dto.request.ReservationRequest;
import com.example.hospitalreservation.reservation.entity.ReasonImage;
import com.example.hospitalreservation.reservation.entity.Reservation;
import com.example.hospitalreservation.reservation.repository.ReasonImageRepository;
import com.example.hospitalreservation.reservation.repository.ReservationRepository;
import com.example.hospitalreservation.reservation.service.ReservationService;
import com.example.hospitalreservation.users.entity.Users;
import com.example.hospitalreservation.users.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional
@Service
public class ReservationServiceImpl implements ReservationService {
    private final HospitalRepository hospitalRepository;
    private final UserRepository userRepository;
    private final ReservationRepository reservationRepository;
    private final ReasonImageRepository reasonImageRepository;
    private final S3Uploader s3Uploader;

    //예약 단건 조회
    @Transactional(readOnly = true)
    @Override
    public Reservation readReservation(CustomPrincipal principal, Long reservationId){
        return reservationRepository.findByUsers_IdAndId(principal.id(), reservationId);

    }

    //userId 로 예약리스트 get
    @Transactional(readOnly = true)
    @Override
    public List<ReservationDto> readReservationList(CustomPrincipal principal){
        return reservationRepository.findByUsers_Id(principal.id())
                .stream()
                .map(ReservationDto::from)
                .collect(Collectors.toList());
    }

    //예약하기
    @Override
    public Reservation createReservation(ReservationRequest reservationRequest, CustomPrincipal principal, Long hospitalId, List<MultipartFile> multipartFiles) throws IOException{
        Hospital hospital = hospitalRepository.findById(hospitalId).orElseThrow(() -> new EntityNotFoundException("병원이 없습니다"));
        Users user = userRepository.getReferenceById(principal.userId());
        Reservation reservation = Reservation.of(reservationRequest.telNo(), reservationRequest.reason(), reservationRequest.reservationDate(), user, hospital);
        List<String> image = s3Uploader.uploads(multipartFiles);
        List<String> imgList = new ArrayList<>();
        for(String imageUrl : image){
            ReasonImage img  = new ReasonImage(imageUrl, reservation);
            reasonImageRepository.save(img);
            imgList.add(img.getImgUrl());
        }
        return reservationRepository.save(reservation);
    }

    //예약수정
    @Override
    public Reservation updateReservation(ReservationRequest reservationRequest, CustomPrincipal principal, Long reservationId){
        Reservation reservation = reservationRepository.findById(reservationId).orElseThrow(() -> new EntityNotFoundException("예약이 없습니다"));
        if(reservationRequest.telNo() != null){
            reservation.setTelNo(reservationRequest.telNo());
        }
        if(reservationRequest.reason() != null){
            reservation.setReason(reservationRequest.reason());
        }
        if(reservationRequest.reservationDate() != null){
            reservation.setReservationDate(reservationRequest.reservationDate());
        }
        return reservationRepository.saveAndFlush(reservation);
    }

    //예약삭제
    @Override
    public void deleteReservation(CustomPrincipal principal, Long hospitalId, Long reservationId){
        reservationRepository.deleteById(reservationId);
    }
}
