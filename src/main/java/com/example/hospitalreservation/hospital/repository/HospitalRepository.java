package com.example.hospitalreservation.hospital.repository;

import com.example.hospitalreservation.hospital.entity.Hospital;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HospitalRepository extends JpaRepository<Hospital, Long> {
    Page<Hospital> findByYadmNmContaining(String yadmNm, Pageable pageable);
}
