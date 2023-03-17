package com.example.hospitalreservation.reservation.repository;

import com.example.hospitalreservation.reservation.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByUsers_Id(Long userId);

    Reservation findByUsers_IdAndId(Long userId, Long reservationId);
}
