package com.example.hospitalreservation.reservation.repository;

import com.example.hospitalreservation.reservation.entity.Reservation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    Page<Reservation> findByUsers_Username(String username, Pageable pageable);

    Reservation findByUsers_usernameAndId(String username, Long reservationId);


}
