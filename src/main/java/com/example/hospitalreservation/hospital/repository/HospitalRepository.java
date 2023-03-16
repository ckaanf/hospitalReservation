package com.example.hospitalreservation.hospital.repository;

import com.example.hospitalreservation.hospital.entity.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HospitalRepository extends JpaRepository<Hospital, Long> {
}
