package com.example.hospitalreservation.reservation.controller;

import com.example.hospitalreservation.config.security.dto.CustomPrincipal;
import com.example.hospitalreservation.pagination.service.PaginationService;
import com.example.hospitalreservation.reservation.dto.request.ReservationRequest;
import com.example.hospitalreservation.reservation.dto.response.ReservationResponse;
import com.example.hospitalreservation.reservation.entity.Reservation;
import com.example.hospitalreservation.reservation.repository.ReservationRepository;
import com.example.hospitalreservation.reservation.service.ReservationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/reservations")
@Controller
public class ReservationController {
    private final ReservationRepository reservationRepository;
    private final ReservationService reservationService;
    private final PaginationService paginationService;


    @GetMapping
    public String reservation(
            @PageableDefault(size = 6, direction = Sort.Direction.DESC) Pageable pageable,
            ModelMap map) {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails) principal;
        String username = userDetails.getUsername();

        log.info("get reservaiton username: {} ", username);

        Page<ReservationResponse> reservations = reservationService.readReservationList(username, pageable);

        log.info("예약병원 목록 : {}", reservations.getContent());

        List<Integer> barNumbers = paginationService.getPaginationBarNumbers(pageable.getPageNumber(), reservations.getTotalPages());

        map.addAttribute("reservations", reservations);
        map.addAttribute("barNumber", barNumbers);

        return "reservation/index";
    }

    //예약 상세 조회
    @GetMapping("/{reservation-id}")
    public String getReservation(
            @PathVariable("reservation-id") Long reservationId,
            ModelMap map) {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails) principal;
        String username = userDetails.getUsername();

        ReservationResponse reservation =reservationService.readReservation(username,reservationId);

        map.addAttribute("reservation", reservation);

        return "reservation/detail";
    }


    @GetMapping("/rest")
    public String getReservationRest(
            @RequestParam String username,
            @PageableDefault(size = 10, direction = Sort.Direction.DESC) Pageable pageable,
            ModelMap map) {

        Page<ReservationResponse> reservations = reservationService.readReservationList(username, pageable);
        List<ReservationResponse> reservationsList = reservations.getContent();
        List<Integer> barNumber = paginationService.getPaginationBarNumbers(pageable.getPageNumber(), reservations.getTotalPages());

        map.addAttribute("reservationList", reservations);

        return "reservation/index";
    }

    //예약 Model
    @PostMapping(value = "/{hospital-id}/reservation", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public String postReservation(
            @PathVariable("hospital-id") Long hospitalId,
            @ModelAttribute ReservationRequest reservationRequest
//            @RequestPart(value = "images", required = false) List<MultipartFile> images
    ) throws IOException {



        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails) principal;
        String username = userDetails.getUsername();

        log.info("get reservaiton username: {} ", username);
        List<MultipartFile> images = reservationRequest.images();


        Reservation reservation = reservationService.createReservation(reservationRequest, username , hospitalId, images);


        return "redirect:/reservations";
    }

    //예약수정
    @PostMapping("/{reservationId}/update")
    public String updateReservation(
            @PathVariable("reservationId") Long reservationId,
            ReservationRequest reservationRequest) throws IOException {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails) principal;
        String username = userDetails.getUsername();

        log.info("get reservaiton username: {} ", username);

        List<MultipartFile> images = reservationRequest.images();

        log.info("reservation update principal : {} ",username);

        reservationService.updateReservation(reservationRequest, username, reservationId, images);

        return "redirect:/reservations" ;
    }


    //예약삭제
    @GetMapping("/{reservationId}/delete")
    public String deleteReservation(@PathVariable("reservationId") Long reservationId) {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails) principal;
        String username = userDetails.getUsername();


        log.info("reservation delete principal : {} ",username);

        reservationService.deleteReservation(username, reservationId);

        return "redirect:/reservations";
    }

}
